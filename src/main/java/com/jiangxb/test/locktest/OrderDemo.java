package com.jiangxb.test.locktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class OrderDemo {

    private static final Logger log = LoggerFactory.getLogger(OrderDemo.class);

    private static ConcurrentHashMap<String, Item> items = new ConcurrentHashMap<>();

    static {
        // 初始化10个商品
        IntStream.range(0, 10).forEach(i -> items.put("item" + i, new Item("item" + i)));
    }

//    public OrderDemo() {
//        // 初始化10个商品
//        IntStream.range(0, 10).forEach(i -> items.put("item" + i, new Item("item" + i)));
//    }

    static class Item {
        // 商品名
        final String name;

        // 剩余库存
        int remaining = 1000;

        ReentrantLock lock = new ReentrantLock();

        public Item(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", remaining=" + remaining +
                    '}';
        }
    }

    private static List<Item> createCart() {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
                .map(name -> items.get(name)).collect(Collectors.toList());
    }

    private static boolean createOrder(List<Item> order) {

        // 存放所有获得的锁
        List<ReentrantLock> locks = new ArrayList<>();

        for (Item item : order) {
            try {
                // 获得锁3秒超时
                if (item.lock.tryLock(3, TimeUnit.SECONDS)) {
                    locks.add(item.lock);
                } else {
                    locks.forEach(ReentrantLock::unlock);
                    return false;
                }
            } catch (InterruptedException e) {

            }
        }

        // 锁全部拿到之后执行扣减库存业务逻辑
        try {
            order.forEach(item -> item.remaining--);
        } finally {
            locks.forEach(ReentrantLock::unlock);
        }
        return true;
    }

    private static void errorOperation(){
        long begin = System.currentTimeMillis();

        // 并发进行50次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 50).parallel()
                .mapToObj(i -> {
                    List<Item> cart = createCart();
                    return createOrder(cart);
                }).filter(result -> result)
                .count();

        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.entrySet().stream().map(item -> item.getValue().remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin,
                items);
    }

    private static void rightOperation(){
        long begin = System.currentTimeMillis();

        // 并发进行50次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 50).parallel()
                .mapToObj(i -> {
                    List<Item> cart = createCart().stream()
                            .sorted(Comparator.comparing(Item::getName))
                            .collect(Collectors.toList());
                    return createOrder(cart);
                }).filter(result -> result)
                .count();

        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.entrySet().stream().map(item -> item.getValue().remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin,
                items);
    }

    /**
     * 模拟下单操作
     * @param args
     */
    public static void main(String[] args) {

//        errorOperation();

        rightOperation();

    }

}



package com.jiangxb.juc.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
* 用wait notifyAll实现时，notifyAll会同时叫醒所有生产者和消费者线程
* 如果消费者notifyAll后又被另一个消费者获得锁，又要再次notifyAll
* 降低了一些效率。
*
* 下个程序中用ReentrantLock改进
*/
public class T03_0_MyContainer<E> {

    final private LinkedList<E> list = new LinkedList<>();
    final private int MAX = 10; // 最多10个元素
    private int count = 0;

    // 生产者
    public synchronized void put(E e) {
        // 这里要用while
        // if在判断了集合的大小等于MAX的时候，
        // 调用了wait()方法以后，它不会再去判断一次，方法会继续往下运行
        while (list.size() >= MAX) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        list.add(e);
        count++;
        this.notifyAll();
    }

    // 消费者
    public synchronized E get() {
        E e = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        e = list.removeFirst();
        count --;
        this.notifyAll();
        return e;
    }

    public static void main(String[] args) {
        T03_0_MyContainer<String> instance = new T03_0_MyContainer<>();

        // 启动10个消费者线程,每个消费者拿出5个产品
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(instance.get());
                }
            }, "消费者" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动两个生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    instance.put(Thread.currentThread().getName() + " 生产 " + j);
                }
            }, "生产者" + i).start();
        }
    }

}

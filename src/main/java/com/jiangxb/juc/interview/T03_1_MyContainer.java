package com.jiangxb.juc.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* ReentrantLock改进版
*/
public class T03_1_MyContainer<E> {

    final private LinkedList<E> list = new LinkedList<>();
    final private int MAX = 10; // 最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    // 生产者
    public void put(E e) {
        try {
            lock.lock();
            // 这里要用while
            // if在判断了集合的大小等于MAX的时候，
            // 调用了wait()方法以后，它不会再去判断一次，方法会继续往下运行
            while (list.size() >= MAX) {
                producer.await();
            }
            list.add(e);
            count++;
            consumer.signalAll(); // 通知consumer线程消费
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 消费者
    public E get() {
        E e = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            e = list.removeFirst();
            count --;
            producer.signalAll(); // 通知producer线程生产
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return e;
    }

    public static void main(String[] args) {
        T03_1_MyContainer<String> instance = new T03_1_MyContainer<>();

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

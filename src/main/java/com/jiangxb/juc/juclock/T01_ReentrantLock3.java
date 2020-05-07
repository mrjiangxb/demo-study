package com.jiangxb.juc.juclock;

import java.util.concurrent.locks.ReentrantLock;

// 指定为公平锁
public class T01_ReentrantLock3 extends Thread {

    // 参数为true是公平锁
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock3 instance = new T01_ReentrantLock3();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }

}

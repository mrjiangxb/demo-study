package com.jiangxb.juc.juclock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁
public class T05_ReadWriteLock {

    private static int value = 1;

    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    // 模拟读操作
    public static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 模拟写操作
    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = v;
            System.out.println("write over !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 起18个读线程
        for (int i = 0; i < 18; i++) {
            new Thread(() -> read(lock)).start();
            // new Thread(() -> read(readLock)).start();
        }
        // 起2个写线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> write(lock, new Random().nextInt())).start();
            // new Thread(() -> write(writeLock, new Random().nextInt())).start();
        }
    }

}

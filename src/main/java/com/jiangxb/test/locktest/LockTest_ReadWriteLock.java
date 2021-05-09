package com.jiangxb.test.locktest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.stream.IntStream;

/**
 * 读写锁效率测试
 */
public class LockTest_ReadWriteLock {

    private static volatile int value = 1;

    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    // 模拟读操作
    public static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over ! value: " + value);
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
            System.out.println("write over ! value: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void run(Lock... lock) {
        // 起18个读线程
        IntStream.rangeClosed(1, 18)
                .forEach(i -> new Thread(() -> read(lock[0])).start());

        // 起2个写线程
        IntStream.rangeClosed(1, 2)
                .forEach(i -> new Thread(() -> write(lock[1], new Random().nextInt())).start());
    }

    public static void main(String[] args) {
        // ReentrantLock 测试
//        run(lock, lock);

        // ReentrantReadWriteLock 测试
        run(readLock, writeLock);

    }
}

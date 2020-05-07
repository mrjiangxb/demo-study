package com.jiangxb.juc.juclock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 可重入锁
public class T01_ReentrantLock1 {

    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock(); // synchronized(this)
            System.out.println("m1 start");
            TimeUnit.SECONDS.sleep(7);
            System.out.println("m1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        boolean locked = false;
        try {
            // locked = lock.tryLock();
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 instence = new T01_ReentrantLock1();
        new Thread(instence::m1).start();
        new Thread(instence::m2).start();
    }
}

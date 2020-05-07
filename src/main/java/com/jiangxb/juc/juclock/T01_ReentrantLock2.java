package com.jiangxb.juc.juclock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T01_ReentrantLock2 {

    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock(); // synchronized(this)
            System.out.println("m1 start");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("m1 end");
        } catch (InterruptedException e) {
            System.out.println("m1 Interrupted!!");
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        boolean locked = false;
        try {
            lock.lockInterruptibly(); // 可以对interrupt()方法做出响应
            System.out.println("m2 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m2 end");
        }  catch (InterruptedException e) {
            System.out.println("m2 Interrupted!!");
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock2 instence = new T01_ReentrantLock2();
        Thread t1 = new Thread(instence::m1);
        Thread t2 = new Thread(instence::m2);
        t1.start();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); // 打断线程2的等待

    }

}

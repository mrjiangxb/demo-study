package com.jiangxb.juc.juclock;

import com.jiangxb.juc.SyncTest_03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 可重入锁
public class T01_ReentrantLock {

    Lock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock(); // synchronized(this)
            System.out.println("m1 start");
            m2(); // m2的第二道锁被释放后才继续往下执行
            System.out.println("m1 end");
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_ReentrantLock instence = new T01_ReentrantLock();
        new Thread(instence::m1).start();
    }
}

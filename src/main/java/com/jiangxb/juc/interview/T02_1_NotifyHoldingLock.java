package com.jiangxb.juc.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/*
* wait  notify 实现
* 使用wait notify要在synchronized下
*/
public class T02_1_NotifyHoldingLock {

    // 添加volatile使t2能够得到通知
    volatile List list = new LinkedList();

    final static Object lock = new Object();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T02_1_NotifyHoldingLock instance = new T02_1_NotifyHoldingLock();

        // 先启动t2 再启动t1
        new Thread(() -> {
            System.out.println("t2 启动");
            synchronized (lock) {
                if (instance.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    instance.add(new Object());
                    System.out.println("add " + i);
                    if (instance.size() == 5) {
                        // 唤醒t2线程
                        lock.notify();
                        try {
                            // 阻塞等待t2线程处理完后唤醒该线程
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }

}

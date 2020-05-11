package com.jiangxb.juc.interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/*
* LockSupport 实现
*/
public class T02_2_LockSupport {

    // 添加volatile使t2能够得到通知
    volatile List list = new LinkedList();

    static Thread t1 = null;
    static Thread t2 = null;


    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T02_2_LockSupport instance = new T02_2_LockSupport();

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                instance.add(new Object());
                System.out.println("add " + i);
                if (instance.size() == 5) {
                    // 唤醒t2线程
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            if (instance.size() != 5) {
                LockSupport.park();
            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        }, "t2");

        t2.start();
        t1.start();

    }

}

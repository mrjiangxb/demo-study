package com.jiangxb.juc.interview;

import java.util.concurrent.locks.LockSupport;

// Locksupport park 当前线程阻塞，暂停运行
// unpart(Thread t)

public class T01_0_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t2); // 唤醒t2
                LockSupport.park(); // 当前t1线程阻塞住，等待被t2唤醒
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char i : aI) {
                LockSupport.park(); // 当前t2线程阻塞住，等待被t2唤醒
                System.out.print(i);
                LockSupport.unpark(t1); // 唤醒t1
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}

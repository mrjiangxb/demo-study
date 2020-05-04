package com.jiangxb.juc.interview;

public class T01_1_sync_wait_notify {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        final  Object o = new Object();

        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        t1 = new Thread(() -> {

            synchronized (o) {
                for (char c : aC) {
                    try {
                        System.out.print(c);
                        o.notify();
                        o.wait(); // 让出锁 等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须notify 否则循环完后会有个线程在wait不能正常结束
            }

        }, "t1");

        t2 = new Thread(() -> {

            synchronized (o) {
                for (char i : aI) {
                    try {
                        o.wait();
                        System.out.print(i);
                        o.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }


        }, "t2");

        // 这里先让打印数字的t2线程start，先wait
        // t1先start的话两个线程会同时wait
        // 先notify 后wait，wait线程不会被唤醒
        t2.start();
        t1.start();

    }

}

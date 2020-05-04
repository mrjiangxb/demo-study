package com.jiangxb.juc;

import java.util.concurrent.TimeUnit;

public class VolatileTest_01 {

    volatile boolean flag = true;

    void m() {
        System.out.println("m start ......");
        while (flag) {
            System.out.println("  flag = "+flag);
        }
        System.out.println("m end !");
    }

    public static void main(String[] args) {
        VolatileTest_01 test = new VolatileTest_01();

        new Thread(test::m, "thread-1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.flag = false;
    }

}

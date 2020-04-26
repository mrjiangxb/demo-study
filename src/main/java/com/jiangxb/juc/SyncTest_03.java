package com.jiangxb.juc;

import java.util.concurrent.TimeUnit;

public class SyncTest_03 {

    public synchronized void m1() {
        System.out.println("m1 start");
        m2();
        System.out.println("m1 end");
    }

    public synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        SyncTest_03 syncTest03 = new SyncTest_03();
        new Thread(syncTest03::m1).start();

    }
}

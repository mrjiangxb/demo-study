package com.jiangxb.juc;

import java.util.concurrent.TimeUnit;

public class SyncTest_02 {

    public synchronized void m1() {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }

    public void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args) {
        SyncTest_02 syncTest02 = new SyncTest_02();
        /*new Thread(() -> syncTest02.m1()).start();
        new Thread(() -> syncTest02.m2()).start();*/
        new Thread(syncTest02::m1).start();
        new Thread(syncTest02::m2).start();
    }


}

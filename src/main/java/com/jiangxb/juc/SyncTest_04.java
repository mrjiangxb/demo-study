package com.jiangxb.juc;

import java.util.concurrent.TimeUnit;

public class SyncTest_04 {

    public synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        Child child = new Child();
        new Thread(child::m).start();
    }
}

class Child extends SyncTest_04 {
    @Override
    public void m() {
        synchronized(new SyncTest_04()){
            System.out.println("child m start");
            super.m();
            System.out.println("child m end");
        }
    }
}

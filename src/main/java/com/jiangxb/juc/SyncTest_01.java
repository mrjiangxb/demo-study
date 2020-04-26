package com.jiangxb.juc;

public class SyncTest_01 implements Runnable {

    /*public synchronized static  void m(){
        synchronized (SyncTest_01.class) {
            // TODO: 方法体......
        }
    }

    public static void mm(){
        synchronized (SyncTest_01.class) {
            // TODO: 方法体......
        }
    }*/

    private volatile int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(
                Thread.currentThread().getName()+"--count=" + count);
    }

    public static void main(String[] args) {
        SyncTest_01 syncTest01 = new SyncTest_01();
        for (int i=0; i<100; i++) {
            new Thread(syncTest01, "Thread" + i).start();
        }
    }

}

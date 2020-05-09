package com.jiangxb.juc.juclock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class T06_Semaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2, true);
        // Semaphore semaphore = new Semaphore(1); // 允许一个线程同时执行

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("thread1 start...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("thread1 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("thread2 start...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("thread2 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }

}

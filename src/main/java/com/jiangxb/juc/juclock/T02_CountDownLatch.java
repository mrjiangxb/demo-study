package com.jiangxb.juc.juclock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T02_CountDownLatch {

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
            // 可设置最长等待时间
            // 超时后若还没countDown到0，门闩自动打开
            // latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

}

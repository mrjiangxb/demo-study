package com.jiangxb.juc.juclock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T03_CyclicBarrier {

    public static void main(String[] args) {

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，栅栏打开");
            }
        });*/

        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("满人，栅栏打开");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    barrier.await(); // 阻塞，直到栅栏打开
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

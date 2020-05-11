package com.jiangxb.juc.juclock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T08_LockSupport {

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // park()方法阻塞当前线程t
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

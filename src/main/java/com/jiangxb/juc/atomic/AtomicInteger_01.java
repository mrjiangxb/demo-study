package com.jiangxb.juc.atomic;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInteger_01 {

    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i=0; i<10000; i++) {
            count.incrementAndGet(); // 自增 count++
        }
        System.out.println("m:"+count);
    }

    public static void main(String[] args) {
        AtomicInteger_01 instance = new AtomicInteger_01();
        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<10; i++) {
            threads.add(new Thread(instance::m, "thread-"+i));
        }

        threads.forEach( thread -> thread.start());

        threads.forEach( thread -> {
            try {
                thread.join(); // 自增线程执行完后才执行主线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(instance.count);

    }

}

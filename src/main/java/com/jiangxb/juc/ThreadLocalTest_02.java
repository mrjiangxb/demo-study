package com.jiangxb.juc;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest_02 {

    // volatile static Person person = new Person();
    static ThreadLocal<Person> personThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 线程t2在自己的threadLocal里初始化person对象后，在该线程中get为null
            System.out.println(personThreadLocal.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在当前线程t2的threadLocal里添加一个person对象
            personThreadLocal.set(new Person());
        }, "t2").start();
    }

}

class Person {
    String name = "zhangsan";
}


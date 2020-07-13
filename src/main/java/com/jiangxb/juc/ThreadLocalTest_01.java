package com.jiangxb.juc;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest_01 {

    volatile static Person person = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出name时，lisi已经被线程t2改为zhangsan
            System.out.println(person.name);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在t1线程没有输出name前，把name改成lisi
            person.name = "lisi";
        }, "t2").start();


    }

}

/*
class Person {
    String name = "zhangsan";
}*/

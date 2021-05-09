package com.jiangxb.test.locktest;

import java.util.stream.IntStream;

/**
 * 静态与非静态的锁定对象
 */
public class LockTest_2 {

    public static void main(String[] args) {

        // 多线程循环一定次数 调用Data类不同实例的add方法
        IntStream.rangeClosed(1, 10_0000)
                .parallel() // 并行流转换
                .forEach(i -> new Data().add());

        System.out.println("new十万个Data对象调用add后: " + Data.getCounter());

        // 多线程循环一定次数 调用Data1类不同实例的add方法
        IntStream.rangeClosed(1, 10_0000)
                .parallel() // 并行流转换
                .forEach(i -> new Data1().add());

        System.out.println("new十万个Data1对象调用add后: " + Data1.getCounter());
    }

}

class Data {
    private static int counter = 0;

    // 在非静态方法上加锁，锁定的是当前对象，
    // 这时多个对象还是共享静态变量counter，仍然有线程安全问题
    public synchronized void add() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }
}

class Data1 {
    private static int counter = 0;

    private static Object locker = new Object();

    // 对静态属性locker加锁,或者在该静态方法上加synchronized,锁定的是class,所有实例的class都是相同的
    // 也就是该类的所有对象用的都是同一把锁
    public void add() {
        synchronized (locker) {
            counter++;
        }
    }

    public static int getCounter() {
        return counter;
    }
}

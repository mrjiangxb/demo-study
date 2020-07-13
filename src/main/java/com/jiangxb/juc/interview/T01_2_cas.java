package com.jiangxb.juc.interview;

// 自旋方式实现
public class T01_2_cas {

    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1; // volatile保证线程可见

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char i : aI) {
                while (r != ReadyToRun.T2) {}
                System.out.print(i);
                r = ReadyToRun.T1;
            }
        }, "t1").start();

    }



}

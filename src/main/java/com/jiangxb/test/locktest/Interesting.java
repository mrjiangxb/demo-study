package com.jiangxb.test.locktest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interesting {

    private static final Logger log = LoggerFactory.getLogger(Interesting.class);

    volatile int a = 1;
    volatile int b = 1;

    public void add() {
        log.info("add start");
        for (int i = 0; i < 1000000; i++) {
            a++;
            b++;
        }
        log.info("add end");
    }

    public void compare() {
        log.info("compare start");
        for (int i = 0; i < 1000000; i++) {
            // a 始终等于 b 吗？
            // 在字节码层面是会先加载 a 再加载 b 后进行比对大小，当加载完a后，到b被加载时 这之间 b可能被add()又++了多次，出现了a < b的情况
            if (a < b) {
                // 比较操作不是原子性的，会造成交插执行时
                log.info("a:{},b:{},{}", a, b, a > b);
                // 最后的 a > b 始终是 false 吗?
            }
        }
        log.info("compare start");
    }

    public static void main(String[] args) {
        Interesting interesting = new Interesting();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();
    }

}

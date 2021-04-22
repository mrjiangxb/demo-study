package com.jiangxb.test.locktest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Interesting {

    private static final Logger logger = LoggerFactory.getLogger(Interesting.class);

    volatile int a = 1;
    volatile int b = 1;

    public void add() {
        logger.info("add start");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        logger.info("add end");
    }

    public void compare() {
        logger.info("compare start");
        for (int i = 0; i < 10000; i++) {
            // a 始终等于 b 吗？
            if (a < b) {
                logger.info("a:{},b:{},{}", a, b, a > b);
                // 最后的 a > b 始终是 false 吗?
            }
        }
        logger.info("compare start");
    }

    public static void main(String[] args) {

        Interesting interesting = new Interesting();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();

    }

}

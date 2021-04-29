package com.jiangxb.test.locktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 锁粒度demo
 */
public class LockTest_3 {

    private static final Logger log = LoggerFactory.getLogger(LockTest_3.class);

    public static void main(String[] args) {

        LockTest_3 lockTest_3 = new LockTest_3();

        List<Integer> data = new ArrayList<>();

        Long begin = System.currentTimeMillis();
        // 多个线程执行1000次
        IntStream.rangeClosed(1, 1000).parallel().forEach(i -> {
            // 粗粒度锁
            synchronized (lockTest_3) {
                lockTest_3.businessMethod();
                data.add(i);
            }
        });
        log.info("took:{}, data.size:{}", System.currentTimeMillis() - begin, data.size());

    }

    private void businessMethod() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
    }
}

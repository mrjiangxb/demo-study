package com.jiangxb.test.java8.defaultmethod;

public interface FourWheeler {

    void run();

    default void print() {
        System.out.println("我是一辆四轮车!");
    }

}

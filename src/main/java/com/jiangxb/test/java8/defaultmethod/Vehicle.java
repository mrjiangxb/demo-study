package com.jiangxb.test.java8.defaultmethod;

/**
 * Java 8 新增了接口的默认方法。
 * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
 * 只需在方法名前面加个 default 关键字即可实现默认方法。
 *
 * 引进默认方法，目的是为了解决接口的修改与现有的实现不兼容的问题。
 */
public interface Vehicle {

    void run();

    // 实现类可以不用实现该方法
    default void print() {
        System.out.println("我是一辆车！");
    }

}

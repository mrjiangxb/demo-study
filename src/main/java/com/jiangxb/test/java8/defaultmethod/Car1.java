package com.jiangxb.test.java8.defaultmethod;

/**
 * @author jiangxiangbo
 * @date 2020/7/22
 * @Description: TODO
 */
public class Car1 implements Vehicle, FourWheeler {
    @Override
    public void run() {
        System.out.println("车跑起来了");
    }

    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
    }
}

package com.jiangxb.juc.interview;

import java.util.LinkedList;
import java.util.List;

/*
* 该方法不成功！
*
* volatile一定要尽量去修饰普通的值，
* 不要去修饰引用值，因为volatile修饰引用类型，这个引用对象指向的是另外一个new出来的对
* 象，如果这个对象里边的成员变量的值改变了，也是无法观察到的
*/
public class T02_0_WithVolatile {

    volatile List list = new LinkedList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T02_0_WithVolatile instance = new T02_0_WithVolatile();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                instance.add(new Object());
                System.out.println("add " + i);
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (instance.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }

}

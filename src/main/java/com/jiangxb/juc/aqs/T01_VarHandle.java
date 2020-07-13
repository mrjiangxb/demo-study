package com.jiangxb.juc.aqs;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class T01_VarHandle {

    int var = 10;
    private static VarHandle varHandle;

    static {
        // 初始化一个handle工厂
        MethodHandles.Lookup l = MethodHandles.lookup();
        try {
            // 初始化o的handle
            varHandle = l.findVarHandle(T01_VarHandle.class, "var", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T01_VarHandle instance = new T01_VarHandle();

        // 通过handle读取变量（读）
        System.out.println(varHandle.get(instance));

        // 通过handle改变变量（写）
        varHandle.set(instance, 20);
        System.out.println(instance.var);

        //进行线程安全的累加操作
        varHandle.getAndAdd(instance, 10);
        System.out.println(instance.var);

        // 通过handle进行CAS操作
        varHandle.compareAndSet(instance, 30, 40);
        System.out.println(instance.var);
    }

}

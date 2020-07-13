package com.jiangxb.reference;

public class T01_NormalReference {

    public static void main(String[] args) {

        OverridFinalize instance = new OverridFinalize();

        instance = null;

        // 手动触发gc
        System.gc();

        // 让main线程停在这里
        while (true) {}
    }

}

class OverridFinalize {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("自动垃圾回收，finalize被调用");
    }
}

package com.jiangxb.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<OverridFinalize> weakReference = new WeakReference<>(new OverridFinalize());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<OverridFinalize> threadLocal = new ThreadLocal<>();
        threadLocal.set(new OverridFinalize());
        threadLocal.remove();


    }

}

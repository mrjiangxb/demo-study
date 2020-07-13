package com.jiangxb.reference;

import java.lang.ref.SoftReference;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

// 软引用很适合缓存使用
public class T02_SoftReference {

    public static void main(String[] args) {

        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(softReference.get());
        System.gc();

        new ConcurrentSkipListMap();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(softReference.get());

        byte[] b = new byte[1024*1024*15];
        System.out.println(softReference.get());
    }

}

package com.jiangxb.test.java8;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author jiangxiangbo
 * @date 2020/7/22
 * @Description: 测试 Java8 Optional类 这是个很方便的类
 *
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 *
 * Optional 类的引入能很好的解决空指针异常。
 */
public class Optional_Java8 {
    public static void main(String[] args) {

        Optional_Java8 instance = new Optional_Java8();

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable -- 允许传递为null参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of -- 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);

        System.out.println(instance.sum(a, b));


    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent -- 判断值是否存在
        System.out.println("第一个参数存在：" + a.isPresent());
        System.out.println("第二个参数存在：" + b.isPresent());

        // Optional.orElse -- 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        // Optional.get -- 获取值，值需要存在
        Integer value2 = b.get();

        return value1 + value2;

    }

}

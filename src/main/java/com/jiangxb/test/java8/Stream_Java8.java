package com.jiangxb.test.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jiangxiangbo
 * @date 2020/7/22
 * @Description: 测试Java8的Stream
 *
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 */
public class Stream_Java8 {
    public static void main(String[] args) {

        /**
         * stream() − 为集合创建串行流。
         *
         * parallelStream() − 为集合创建并行流。
         */

        /**
         * filter
         * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
         */
        List<String> strings = Arrays.asList("asd", "", "zxc", "asd", "qwe", "", "er");
        // filter() 内参数要实现一个函数式接口，最后.collect将流再转换为集合
        List<String> filteredStr = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("filteredStr:   " + filteredStr);
        System.out.println("空字符串的数量:   " + count);

        System.out.println("+--------------------------------+");

        /**
         * foreach
         * Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
         * limit 方法用于获取指定数量的流
         */
        Random random = new Random();
        // 转换成Stream
        IntStream intStream = random.ints();
        intStream.limit(10).forEach(System.out::println);

        System.out.println("+--------------------------------+");

        /**
         * sorted
         * sorted 方法用于对流进行排序
         */
        random.ints().limit(10).sorted().forEach(System.out::println);

        System.out.println("+--------------------------------+");

        /**
         * map
         * map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
         */
        List<Integer> numbers = Arrays.asList(6, 3, 3, 2, 11, 2, 6);
        // 获取对应的平方数 ( .distinct()去重 )
        Stream<Integer> squaresList = numbers.stream().map(i -> i * i).distinct();
        squaresList.forEach(System.out::println);

        System.out.println("+--------------------------------+");

        /**
         * Collectors
         * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串
         */
        System.out.println("筛选列表：" + filteredStr);
        String mergedString = strings.parallelStream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串：" + mergedString);

        System.out.println("+--------------------------------+");

        /**
         * 统计
         * 一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果
         */
        IntSummaryStatistics statistics = numbers.stream().mapToInt(x -> x*2).summaryStatistics();
        System.out.println("列表中最大的数:" + statistics.getMax());
        System.out.println("列表中最小的数:" + statistics.getMin());
        System.out.println("所有数之和:" + statistics.getSum());
        System.out.println("平均数:" + statistics.getAverage());
        System.out.println(statistics.toString());

        System.out.println("+--------------------------------+");

    }
}

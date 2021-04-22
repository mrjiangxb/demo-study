package com.jiangxb.algorithm.search;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/7/23
 * @Description: 插值查找 类似于二分查找，不同的是插值查找每次从自适应的mid处开始查找
 *
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 * 关键字分布不均匀的情况下，该方法不一定比二分查找好
 *
 * mid = left + (right - left)/2  ----->  mid = left + (right - left)*(target - arr[left]) / (arr[right] - arr[left])
 *
 * Java中常用的查找有四种：
 * 1) 顺序(线性)查找
 * 2) 二分查找/折半查找
 * 3) 插值查找
 * 4) 斐波那契查找
 */
// TODO: 待完善代码
public class FibonacciSearch {

    public static void main(String[] args) {

        int arr[] = {-2, 4, 6, 12, 16, 22, 33};

        FibonacciSearch fibonacciSearch = new FibonacciSearch();

        fibonacciSearch.fib();

    }

    private int fibonacciSearch(int arr[], int leftIndex, int rightIndex, int target) {

        // 表示斐波那契数列的下标
        int k = 0;

        return -1;

    }

    /**
     * 获取长度为20的斐波那契数列
     * @return 斐波那契数列
     */
    private int[] fib() {
        int f[] = new int[20];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        System.out.println(Arrays.toString(f));
        return f;
    }

}

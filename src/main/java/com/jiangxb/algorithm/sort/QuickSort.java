package com.jiangxb.algorithm.sort;

import io.netty.resolver.dns.RoundRobinDnsAddressResolverGroup;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/7/16
 * @Description: 快速排序
 *
 * 1. 选取第一个数为基准
 * 2. 将比基准小的数换到前面，比基准大的数换到后面
 * 3. 对左右区间重复第二步，直到各区间只有一个数
 *
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {6, 21, 5, 9, 10, 2, 16};
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        System.out.println("原数组：" + Arrays.toString(arr));

        quickSort(arr, left, right);

        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        // 基准值 mid
        int mid = arr[(left + right) / 2];
        int temp = 0;

        while (l < r) {
            // 退出while时，就在mid左侧找到了大于等于mid的值的下标
            while (arr[l] < mid) {
                l++;
            }
            // 退出while时，就在mid右侧找到了小于等于mid的值的下标
            while (arr[r] > mid) {
                r--;
            }
            /**
             * 如果left >= right，说明mid左右两边的值
             * 已经左边全小于mid，右边全大于mid
             */
            if (l >= r) {
                break;
            }

            // 将左边大于mid的值与右边小于mid的值交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换后若 arr[l] == mid,r--前移
            if (arr[l] == mid) {
                r--;
            }

            // 交换后若 arr[r] == mid,l++后移
            if (arr[r] == mid) {
                l++;
            }
        }

        // 若left == right,必须left++,right--,否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }

        // 左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}

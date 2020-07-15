package com.jiangxb.algorithm.sort;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/7/13
 * @Description: 希尔排序 - 交换法
 *
 * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 2. 按增量序列个数k，对序列进行k 趟排序；
 * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {5, 21, 9, 6, 10, 2, 16};
        int len = arr.length;
        int temp = 0;
        int count = 0;

        System.out.println("原数组：" + Arrays.toString(arr));
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 分成的组数为gap
            for (int i = gap; i < len; i++) {
                // 遍历各组中所有的元素，步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"轮："+Arrays.toString(arr));
        }
    }

}

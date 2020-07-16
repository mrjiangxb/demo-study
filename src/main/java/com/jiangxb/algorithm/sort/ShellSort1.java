package com.jiangxb.algorithm.sort;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/7/13
 * @Description: 希尔排序 - 移动法
 *
 * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 2. 按增量序列个数k，对序列进行k 趟排序；
 * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 */
public class ShellSort1 {

    public static void main(String[] args) {

        int[] arr = {5, 21, 9, 6, 10, 2, 16, 43, 12, 66, 24};
        int len = arr.length;
        int count = 0;

        System.out.println("原数组：" + Arrays.toString(arr));

        // 增量为gap，并逐步缩小增量
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在组进行直接插入排序
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    // 移动 这里和插入排序是一样的原理
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // while退出后就给temp找到了插入位置
                arr[j] = temp;
            }
            System.out.println("第"+(++count)+"轮："+Arrays.toString(arr));
        }
    }

}

package com.jiangxb.algorithm.sort;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/6/23
 * @Description: 插入排序
 *
 * 1.从第一个元素开始，该元素可以认为已经被排序
 * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5.将新元素插入到该位置后
 * 6.重复步骤2~5
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {5, 2, 9, 6, 10, 21, 16};
        int len = arr.length;

        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < len; i++) {
            // 待插入的数
            insertVal = arr[i];
            // arr[i]前一个数的下标
            insertIndex = i - 1;

            /**
             * 为insertVal找插入位置
             * 1. insertIndex >= 0 保证下标不越界
             * 2. insertVal < arr[insertIndex]待插入的数，说明还未找到插入位置
             * 3. 将arr[insertIndex]后移一个位置（赋值给后一个位置），覆盖后面的数。
             *    如第一轮时为[5, 5, 9, 6, 10, 21, 16]，找到插入位置0后将insertVal=2赋值给arr[0]覆盖原有的5
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            // 退出while循环时说明插入位置找到
            // 因为退出while循环前多执行了一次insertIndex--，所以 insertIndex + 1 才是要插入的位置
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第"+i+"轮："+Arrays.toString(arr));

        }
        System.out.println(Arrays.toString(arr));
    }

}

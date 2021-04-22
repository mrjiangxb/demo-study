package com.jiangxb.algorithm.sort;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/6/7
 * @Description: 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = {5, 2, 9, 6, 10, 21, 16};
        int len = arr.length;

        // 控制循环次数
        for (int i = 0; i < len - 1; i++) {

            // 控制每次循环的比较次数
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }


}

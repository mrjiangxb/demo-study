package com.jiangxb.algorithm.sort;

import java.time.temporal.Temporal;
import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/6/7
 * @Description: 选择排序
 *
 * 1.在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * 2.从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
 * 3.以此类推，直到所有元素均排序完毕
 */
public class SelectionSort {

    public static void main(String[] args) {

        int[] arr = {5, 2, 9, 6, 10, 21, 16};
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }

        System.out.println(Arrays.toString(arr));

    }

}

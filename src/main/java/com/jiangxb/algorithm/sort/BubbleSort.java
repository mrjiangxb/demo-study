package com.jiangxb.algorithm.sort;

import javax.imageio.metadata.IIOMetadataFormatImpl;
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
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }


}

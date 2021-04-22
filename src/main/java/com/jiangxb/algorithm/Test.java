package com.jiangxb.algorithm;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {


        int[] arr = {5, 2, 9, 6, 10, 21, 16};
        int len = arr.length;

        for (int i = 1; i < len; i++) {

            int insertIndex = i - 1;
            int insertVal = arr[i];

            while (insertIndex > -1 && insertVal < arr[insertIndex]) {
                arr[i] = arr[insertIndex];
                insertIndex --;
            }

            arr[insertIndex + 1] = insertVal;

        }


        System.out.println(Arrays.toString(arr));

    }



}

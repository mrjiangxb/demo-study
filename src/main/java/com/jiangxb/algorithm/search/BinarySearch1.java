package com.jiangxb.algorithm.search;

/**
 * @author jiangxiangbo
 * @date 2020/7/23
 * @Description: 二分查找/折半查找 数组必须有序！
 *
 * Java中常用的查找有四种：
 * 1) 顺序(线性)查找
 * 2) 二分查找/折半查找
 * 3) 插值查找
 * 4) 斐波那契查找
 */
public class BinarySearch1 {

    public static void main(String[] args) {

        int arr[] = {-2, 3, 7, 12, 16, 22, 33};

        BinarySearch1 binarySearch = new BinarySearch1();
        int index = binarySearch.binarySearch(arr, 0, arr.length - 1, 33);

        if (index == -1) {
            System.out.println("未找到值");
        } else {
            System.out.println("所找值的下标为：" + index);
        }

    }

    private int binarySearch(int arr[], int leftIndex, int rightIndex, int target) {

        // 当 leftIndex > rightIndex 说明没找到target值
        if (leftIndex > rightIndex) {
            return -1;
        }

        // 否则 还没查找完成，继续查找
        int mid = (leftIndex + rightIndex) / 2;

        int midVal = arr[mid];

        if (target > midVal){
            // 向右递归
            return binarySearch(arr, mid + 1, rightIndex, target);
        } else if (target < midVal) {
            // 向左递归
            return binarySearch(arr, leftIndex, mid - 1, target);
        } else {
            return mid;
        }

    }

}

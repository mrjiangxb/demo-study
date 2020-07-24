package com.jiangxb.algorithm.search;

/**
 * @author jiangxiangbo
 * @date 2020/7/23
 * @Description: 斐波那契查找 与二分查找、插值查找类似，仅仅改变的是中间点mid的位置
 *
 *
 * mid = left + F(k-1)-1     F 代表斐波那契数列
 *
 * Java中常用的查找有四种：
 * 1) 顺序(线性)查找
 * 2) 二分查找/折半查找
 * 3) 插值查找
 * 4) 斐波那契查找
 */
public class InsertValueSearch {

    public static void main(String[] args) {

        int arr[] = {-2, 4, 6, 12, 16, 22, 33};

        InsertValueSearch insertValueSearch = new InsertValueSearch();
        int index = insertValueSearch.insertValueSearch(arr, 0, arr.length - 1, 6);

        if (index == -1) {
            System.out.println("未找到值");
        } else {
            System.out.println("所找值的下标为：" + index);
        }

    }

    private int insertValueSearch(int arr[], int leftIndex, int rightIndex, int target) {

        if (leftIndex > rightIndex || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }

        int mid = leftIndex + (rightIndex - leftIndex)*(target - arr[leftIndex]) / (arr[rightIndex] - arr[leftIndex]);
        int midVal = arr[mid];

        if (target > midVal) {
            // 右递归
            return insertValueSearch(arr, mid + 1, rightIndex, target);
        } else if (target < midVal) {
            // 左递归
            return insertValueSearch(arr, leftIndex, mid - 1, target);
        } else {
            return mid;
        }

    }



}

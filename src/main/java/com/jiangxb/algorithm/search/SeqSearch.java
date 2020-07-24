package com.jiangxb.algorithm.search;

/**
 * @author jiangxiangbo
 * @date 2020/7/23
 * @Description: 顺序(线性)查找 (也就是依次比较)
 *
 * Java中常用的查找有四种：
 * 1) 顺序(线性)查找
 * 2) 二分查找/折半查找
 * 3) 插值查找
 * 4) 斐波那契查找
 */
public class SeqSearch {

    public static void main(String[] args) {

        int arr[] = {-2, 3, 1, 4, -3, 10, 6};

        SeqSearch seqSearch = new SeqSearch();

        int index = seqSearch.seqSearch(arr, -3);

        if (index == -1) {
            System.out.println("未找到值");
        } else {
            System.out.println("所找值的下标为：" + index);
        }

    }

    private   int seqSearch(int arr[], int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

}

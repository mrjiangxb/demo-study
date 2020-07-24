package com.jiangxb.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxiangbo
 * @date 2020/7/23
 * @Description: 二分查找 找到多个与目标值相同的值的下标
 *
 * Java中常用的查找有四种：
 * 1) 顺序(线性)查找
 * 2) 二分查找/折半查找
 * 3) 插值查找
 * 4) 斐波那契查找
 */
public class BinarySearch {

    public static void main(String[] args) {

        int arr[] = {-2, 3, 7, 7, 7, 12, 16, 22, 33, 33};

        BinarySearch binarySearch = new BinarySearch();
        List<Integer> indexList = binarySearch.binarySearch(arr, 0, arr.length - 1, 33);

        if (indexList == null) {
            System.out.println("未找到值");
        } else {
            System.out.println("所找值的下标为：" + indexList.toString());
        }

    }

    private List<Integer> binarySearch(int arr[], int leftIndex, int rightIndex, int target) {

        // 当 leftIndex > rightIndex 说明没找到target值
        if (leftIndex > rightIndex) {
            return null;
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
            // 找到一个目标值后先不返回，扫描该值前后是否有目标值，加入到List中
            ArrayList<Integer> indexList = new ArrayList<>();
            indexList.add(mid);

            // 向 midIndex 左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != target) {
                    break;
                }
                indexList.add(temp);
                temp -= 1;
            }

            // 向 midIndex 右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }

            return indexList;

        }

    }

}

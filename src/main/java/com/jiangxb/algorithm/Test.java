package com.jiangxb.algorithm;

public class Test {

    public static void main(String[] args) {

        // 123
        boolean flag = Solution.isPalinddrome(-121);
        System.out.println(flag);

    }

}

class Solution {
    public static int reverse(int x) {
        long temp = 0;
        while (x != 0) {
            int pop = x % 10;
            temp = temp * 10 + pop;
            // Integer的溢出判断
            // -2的31次方-1 = -2147483647，2的31次方 = 2147483648
            // 还可用该条件作为溢出判断：temp != (int) temp;
            if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
                return 0;
            }
            x = x/10;
        }
        return (int) temp;
    }

    public static boolean isPalinddrome(int x) {
        long temp = 0;
        int copy = x;
        // 获取反转后的数 temp
        while (copy > 0) {
            int pop = copy % 10;
            temp = temp * 10 + pop;
            // 若溢出 直接返回false
            if (temp != (int) temp) {
                return false;
            }
            copy /= 10;
        }

        // 若 temp == x 则为回文数
        if (temp == x) {
            return true;
        }

        return false;
    }
}

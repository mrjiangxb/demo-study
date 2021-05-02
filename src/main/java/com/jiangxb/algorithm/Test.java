package com.jiangxb.algorithm;

public class Test {

    public static void main(String[] args) {

        // 123
        int reverse = Solution.reverse(2147483641);
        System.out.println(reverse);

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
}

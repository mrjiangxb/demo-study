package com.jiangxb.algorithm;


public class Test {

    public static void main(String[] args) {

        int result = Solution.romanToInt("DCXXI");
        System.out.println(result);

    }

}

class Solution {

    public static int romanToInt(String s) {

        int result = 0;
        int preNum = getInt(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getInt(s.charAt(i));
            if (preNum < num) {
                result -= preNum;
            } else {
                result += preNum;
            }
            preNum = num;
        }
        // 每一轮加减的都是上一轮的数，所以这里要加上最后一轮的数
        result += preNum;
        return result;

    }

    private static int getInt(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                break;
        }
        return 0;
    }

    /**
     * 回文数判断
     * @param x
     * @return
     */
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

    /**
     * 反转数字
     * @param x
     * @return
     */
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

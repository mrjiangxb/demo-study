package com.jiangxb.algorithm;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        int result = Solution.removeDuplicates(new int[]{0, 0, 1, 1, 2, 2, 3});
        System.out.println(result);

    }

}

class Solution {

    public static int removeDuplicates(int[] nums) {
        // [0, 0, 1, 1, 2, 2, 3]

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int fast = 1;
        int slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 20.有效的括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>(){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 若当前字符是右括号，进行匹配；若是左括号，入栈
            if (pairs.containsKey(ch)) {
                // 若栈中的左括号为空，或栈顶括号不与当前的右括号匹配，返回false
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                // 若匹配 栈顶被匹配到的括号出栈
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    /**
     * 最长公共前缀
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        // strs = ["flower","flow","flight"]

        if (strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < prefix.length() && j < strs[i].length(); j++) {
                if (strs[i].charAt(j) != prefix.charAt(j)) {
                    break;
                }
            }
            prefix = prefix.substring(0, j);
        }

        return prefix;

    }

    /**
     * 罗马数字转阿拉伯数字
     * @param s
     * @return
     */
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

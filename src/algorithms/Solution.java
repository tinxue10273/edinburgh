/*
给定一个字符串，打印出不存在相同字符的最长子字符串（不使用任何库函数），例如︰输入"abcabbdacbefbac",打印出最长子字符串为"dacbef";
 */

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String res = getLongestSubStr("abcabbdacbefbac");
        System.out.println(res);
    }

    public static String getLongestSubStr(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] preIndex = new int[265];
        Arrays.fill(preIndex, -1);
        int maxLen = 0;
        int curLen = 0;
        int[] maxIndex = new int[2];
        int[] curIndex = new int[2];
        for (int i = 0; i < s.length(); i++) {
            int curI = i;
            int preI = preIndex[s.charAt(i) - ' '];
            if (preI == -1 || curI - preI > curLen) {
                curIndex[1] = i;
                curLen++;
            } else {
                if (maxLen < curLen) {
                    maxIndex[0] = curIndex[0];
                    maxIndex[1] = curIndex[1];
                    maxLen = curLen;
                }
                curLen = curI - preI;
                curIndex[0] = preI + 1;
                curIndex[1] = curI;
            }
            preIndex[s.charAt(i) - ' '] = i;
        }
        return curLen > maxLen ? s.substring(curIndex[0], curIndex[1] + 1) : s.substring(maxIndex[0], maxIndex[1] + 1);
    }
}
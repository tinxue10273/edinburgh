/*
给定一个字符串，打印出不存在相同字符的最长子字符串（不使用任何库函数），例如︰输入"abcabbdacbefbac",打印出最长子字符串为"dacbef";
 */

import java.util.TreeSet;

/*
给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

如果存在则返回 true，不存在返回 false。

输入：nums = [1,2,3,1], k = 3, t = 0
输出：true
 */
public class Solution {

    public static void main(String[] args) {
        boolean res = is(new int[] {1,2,3,1}, 3, 0);
        System.out.println(res);
    }

    public static boolean is(int[] nums, int k, int t){
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j <= i + k && j < nums.length; j++){
                if(Math.abs(nums[j] - nums[i]) <= t){
                    return true;
                }
            }
        }
        return false
    }


    /*
    private  size = t + 1;
    num / size

    1 分桶
    for{ i num = nums[i]
    2 num -> 桶no
    3 桶no里是否有值 有 true
    4 桶左 桶右 是否 符合 t 是 true
    5 i - k >= 0 得到 num[i-k] 的 桶的位子 并弹出
    }
    6 返回 false
     */


}
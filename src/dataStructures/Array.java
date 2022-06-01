package dataStructures;

import java.util.HashMap;
import java.util.Map;

public class Array {
        public int[] subarraySum(int[] nums, int k) {
            Map<Integer, Integer> mapLeft = new HashMap<>();
            int left = -1;
            int right = 0;
            int maxLen = 0;
            int[] sums = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            //mapLeft.put(k, 0);
            for (int i = 0; i < sums.length; i++) {
                int sum = sums[i];
                Integer index = mapLeft.get(sum - k);
                if (index == null) {
                    mapLeft.put(sum, mapLeft.getOrDefault(sum, i));
                    continue;
                }
                if (i - index > maxLen) {
                    left = index;
                    right = i;
                    maxLen = i - index;
                }
            }
            if (maxLen == 0) {
                return new int[]{};
            }
            int[] res = new int[maxLen];
            int index = 0;
            for (int i = left; i < right; i++) {
                res[index++] = nums[i];
            }
            return res;
        }

        public static void main(String[] args) {
            Array array = new Array();
            int[] res = array.subarraySum(new int[]{-1, -2, -3, 0, 6, 2, 0, 0}, 2);
        }
}

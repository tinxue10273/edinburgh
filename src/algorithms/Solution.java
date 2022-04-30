import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnts = new int[26];
        for (char ch : tasks) {
            cnts[ch - 'A']++;
        }
        Arrays.sort(cnts);
        int maxCnt = 0;
        for (int i = 25; i >= 0; i--) {
            if (cnts[i] == cnts[25]) {
                maxCnt++;
            } else {
                break;
            }
        }
        return Math.max((cnts[25] - 1) * (n + 1) + maxCnt, tasks.length);
    }
}
package dataStructures;

/**
 * 动态规划
 */
public class Dp {

    /**
     * 1 圆环回原点问题
     * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
     * 输入: 2
     * 输出: 2
     * 解释：有2种方案。分别是0->1->0和0->9->0
     */
    public int circleSteps(int n, int m) {
        int[][] dp = new int[m + 1][n];
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][(j + 1) % n] + dp[i - 1][(j - 1 + n) % n];
            }
        }
        return dp[m][0];
    }
}

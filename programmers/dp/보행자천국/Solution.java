package programmers.dp.보행자천국;

public class Solution {

    static final int MOD = 20170805;

    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m + 1][n + 1][2];

        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    dp[i + 1][j][0] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i][j + 1][1] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                } else if (cityMap[i][j] == 2) {
                    dp[i + 1][j][0] += dp[i][j][0] % MOD;
                    dp[i][j + 1][1] += dp[i][j][1] % MOD;
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] cityMap = {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };

        System.out.println(solution(m, n, cityMap));
    }
}
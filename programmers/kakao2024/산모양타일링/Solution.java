package programmers.kakao2024.산모양타일링;

public class Solution {

    final static int MOD = 10007;

    public static int solution(int n, int[] tops) {
        // [i[[0] : i번째 가운데 삼각형을 \\ 모양의 마름모로 채우지 않은 경우
        // [i][1] : i번째 가운데 삼각형을 \\ 모양의 마름모로 채운 경우
        int[][] dp = new int[n + 1][2];

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = dp[i][0] * (2 + tops[i]) + dp[i][1] * (1 + tops[i]);
            dp[i + 1][0] %= MOD;

            dp[i + 1][1] = dp[i][0] + dp[i][1];
            dp[i + 1][1] %= MOD;
        }

        return (dp[n][0] + dp[n][1]) % MOD;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] tops = { 1, 1, 0, 1 };

        System.out.println(solution(n, tops));
    }
}
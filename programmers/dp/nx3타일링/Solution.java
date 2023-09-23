package programmers.dp.nx3타일링;

public class Solution {

    public static int solution(int n) {
        int mod = 1000000007;
        long[] dp = new long[n + 1];

        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
            dp[i] = dp[i] % mod;
        }

        // for (int i = 4; i <= n; i += 2) {
        // dp[i] = (dp[i - 2] * 4 % mod - dp[i - 4] % mod + mod) % mod;
        // }

        return (int) dp[n];
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(solution(n));
    }
}
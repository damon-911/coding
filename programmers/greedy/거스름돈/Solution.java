package programmers.greedy.거스름돈;

public class Solution {

    static final int mod = 1_000_000_007;

    public static int solution(int n, int[] money) {
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m] % mod;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] money = { 1, 2, 5 };

        System.out.println(solution(n, money));
    }
}

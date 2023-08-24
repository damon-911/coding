package programmers.dp.점프와순간이동;

public class Solution {

    public static int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n--;
                ans++;
            }
        }

        // int[] dp = new int[n + 1];
        // dp[1] = 1;
        // for (int i = 2; i <= n; i++) {
        // if (i % 2 == 0) {
        // dp[i] = Math.min(dp[i - 1] + 1, dp[i / 2]);
        // } else {
        // dp[i] = Math.min(dp[i - 1] + 1, dp[i / 2] + 1);
        // }
        // }
        // ans = dp[n];

        return ans;
    }

    public static void main(String[] args) {
        int N = 5;

        System.out.println(solution(N));
    }
}
package programmers.dp.멀리뛰기;

public class Solution {

    public static long solution(int n) {
        long[] dp = new long[2001];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 2000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(solution(n));
    }
}
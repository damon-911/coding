package programmers.dp.땅따먹기;

public class Solution {

    public static int solution(int[][] land) {
        int answer = 0;

        int[][] dp = land.clone();

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] += Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][1] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3]));
            dp[i][2] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][3]));
            dp[i][3] += Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
        }

        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] land = {
                { 1, 2, 3, 5 },
                { 5, 6, 7, 8 },
                { 4, 3, 2, 1 }
        };

        System.out.println(solution(land));
    }
}
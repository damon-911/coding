package programmers.dp.정수삼각형;

public class Solution {

    public static int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j],
                            dp[i - 1][j] + triangle[i][j]);

                if (i == triangle.length - 1)
                    answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] triangle = {
                { 7 },
                { 3, 8 },
                { 8, 1, 0 },
                { 2, 7, 4, 4 },
                { 4, 5, 2, 6, 5 }
        };

        System.out.println(solution(triangle));
    }
}
package programmers.dp.최적의행렬곱셈;

public class Solution {

    public static int solution(int[][] matrix_sizes) {
        int N = matrix_sizes.length;

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                int a = j;
                int b = j + i;

                if (a == b) {
                    dp[a][b] = 0;
                } else {
                    for (int k = a; k < b; k++) {
                        dp[a][b] = Math.min(dp[a][b],
                                dp[a][k] + dp[k + 1][b] + matrix_sizes[a][0] * matrix_sizes[k][1] * matrix_sizes[b][1]);
                    }
                }
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        int[][] matrix_sizes = {
                { 5, 3 },
                { 3, 10 },
                { 10, 6 }
        };

        System.out.println(solution(matrix_sizes));
    }
}
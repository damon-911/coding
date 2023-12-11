package programmers.dp.에어컨;

import java.util.Arrays;

public class Solution {

    static final int INF = 100_000;

    public static int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // temperature는 t2보다 큰 값이거나 t1보다 작은 값
        int temp = (temperature > t2) ? t1 - (temperature - t2) : temperature;
        t1 -= temp;
        t2 -= temp;
        temp = 0;

        int[][] dp = new int[onboard.length][t2 + 2]; // dp[a][b] : a분에 온도 b를 맞추기 위한 최소 비용
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j <= t2 + 1; j++) {
                // 탑승 중일때 온도 범위 벗어나면 구하지 않음
                if (onboard[i] == 1 && (j < t1 || j > t2))
                    continue;

                int min = INF;

                // 1. 외부온도 == 목표온도
                if (j == 0) {
                    // 온도 유지하기
                    min = Math.min(min, dp[i - 1][j]);
                    // 온도 내리기
                    if (j + 1 <= t2 + 1)
                        min = Math.min(min, dp[i - 1][j + 1]);
                }
                // 2. 외부온도 != 목표온도
                else {
                    // 온도 올리기
                    if (j - 1 >= 0)
                        min = Math.min(min, dp[i - 1][j - 1] + a);
                    // 온도 유지하기
                    min = Math.min(min, dp[i - 1][j] + b);
                    // 온도 내리기
                    if (j + 1 <= t2 + 1)
                        min = Math.min(min, dp[i - 1][j + 1]);
                }

                dp[i][j] = min;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = 0; j <= t2 + 1; j++) {
            result = Math.min(result, dp[onboard.length - 1][j]);
        }

        return result;

        // int[][] dp = new int[51][onboard.length + 1];
        // temperature += 10;
        // t1 += 10;
        // t2 += 10;

        // for (int i = onboard.length - 1; i >= 0; i--) {
        // for (int t = 0; t < 51; t++) {
        // dp[t][i] = Integer.MAX_VALUE - Math.max(a, b);

        // if (onboard[i] == 1 && (t < t1 || t > t2))
        // continue;

        // if (t == temperature) {
        // dp[t][i] = Math.min(dp[t][i], dp[t][i + 1]);
        // } else if (t > 0 && t > temperature) {
        // dp[t][i] = Math.min(dp[t][i], dp[t - 1][i + 1]);
        // } else if (t < 50 && t < temperature) {
        // dp[t][i] = Math.min(dp[t][i], dp[t + 1][i + 1]);
        // }

        // if (t < 50) {
        // dp[t][i] = Math.min(dp[t][i], dp[t + 1][i + 1] + a);
        // }

        // if (t > 0) {
        // dp[t][i] = Math.min(dp[t][i], dp[t - 1][i + 1] + a);
        // }

        // dp[t][i] = Math.min(dp[t][i], dp[t][i + 1] + b);
        // }
        // }

        // return dp[temperature][0];
    }

    public static void main(String[] args) {
        int temperature = 28;
        int t1 = 18;
        int t2 = 26;
        int a = 10;
        int b = 8;
        int[] onboard = { 0, 0, 1, 1, 1, 1, 1 };

        System.out.println(solution(temperature, t1, t2, a, b, onboard));
    }
}
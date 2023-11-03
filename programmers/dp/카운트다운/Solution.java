package programmers.dp.카운트다운;

import java.util.Arrays;

public class Solution {

    static int[][] dp;

    static int[] playDart(int num) {
        if (dp[num][0] == Integer.MAX_VALUE) {
            if (num >= 50) { // 불 맞추기
                int[] temp = playDart(num - 50);

                if ((temp[0] + 1 < dp[num][0])
                        || (temp[0] + 1 == dp[num][0] && temp[1] + 1 > dp[num][1])) {
                    dp[num][0] = 1 + temp[0];
                    dp[num][1] = 1 + temp[1];
                }
            }

            int start = num >= 20 ? 20 : num;
            for (int i = start; i >= 1; i--) {
                for (int j = 1; j <= 3; j++) { // 싱글, 더블, 트리플
                    if (num >= i * j) {
                        int[] temp = playDart(num - i * j);
                        int singleCnt = j == 1 ? 1 : 0;

                        if ((temp[0] + 1 < dp[num][0])
                                || (temp[0] + 1 == dp[num][0] && temp[1] + singleCnt > dp[num][1])) {
                            dp[num][0] = 1 + temp[0];
                            dp[num][1] = singleCnt + temp[1];
                        }
                    }
                }
            }
        }

        return dp[num];
    }

    public static int[] solution(int target) {
        dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        return playDart(target);
    }

    public static void main(String[] args) {
        int target = 21;

        System.out.println(Arrays.toString(solution(target)));
    }
}
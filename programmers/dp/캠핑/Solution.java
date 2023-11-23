package programmers.dp.캠핑;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Solution {

    static int[][] dp;
    static int answer;

    static void findCount(int n, int[][] data) {
        // dp(구간합) 구하기
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r - 1 >= 0) {
                    dp[r][c] += dp[r - 1][c];
                }

                if (c - 1 >= 0) {
                    dp[r][c] += dp[r][c - 1];
                }

                if (r - 1 >= 0 & c - 1 >= 0) {
                    dp[r][c] -= dp[r - 1][c - 1];
                }
            }
        }

        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = Math.min(data[i][0], data[j][0]);
                int y1 = Math.min(data[i][1], data[j][1]);
                int x2 = Math.max(data[i][0], data[j][0]);
                int y2 = Math.max(data[i][1], data[j][1]);

                if (x1 == x2 || y1 == y2) {
                    continue;
                }

                int count = dp[x2 - 1][y2 - 1] - dp[x1][y2 - 1] -
                        dp[x2 - 1][y1] + dp[x1][y1];

                if (count == 0) {
                    answer++;
                }
            }
        }
    }

    public static int solution(int n, int[][] data) {
        answer = 0;

        dp = new int[n + 1][n + 1];

        // 좌표 압축
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for (int[] d : data) {
            xList.add(d[0]);
            yList.add(d[1]);
        }

        List<Integer> uniqueXList = new ArrayList<>(new HashSet<Integer>(xList));
        List<Integer> uniqueYList = new ArrayList<>(new HashSet<Integer>(yList));

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        for (int i = 0; i < n; i++) {
            data[i][0] = uniqueXList.indexOf(xList.get(i));
            data[i][1] = uniqueYList.indexOf(yList.get(i));
            dp[data[i][0]][data[i][1]] = 1;
        }

        findCount(n, data);

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] data = {
                { 0, 0 },
                { 1, 1 },
                { 0, 2 },
                { 2, 0 }
        };

        System.out.println(solution(n, data));
    }
}
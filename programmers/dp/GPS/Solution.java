package programmers.dp.GPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[][] dp = new int[k][n + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, k + 1);
        }

        dp[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);

                for (int num : graph.get(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][num]);
                }

                dp[i][j] += (gps_log[i] == j) ? 0 : 1;
            }
        }

        return (dp[k - 1][gps_log[k - 1]] > k) ? -1 : dp[k - 1][gps_log[k - 1]];
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 10;
        int[][] edge_list = {
                { 1, 2 },
                { 1, 3 },
                { 2, 3 },
                { 2, 4 },
                { 3, 4 },
                { 3, 5 },
                { 4, 6 },
                { 5, 6 },
                { 5, 7 },
                { 6, 7 }
        };
        int k = 6;
        int[] gps_log = { 1, 2, 3, 3, 6, 7 };

        System.out.println(solution(n, m, edge_list, k, gps_log));
    }
}

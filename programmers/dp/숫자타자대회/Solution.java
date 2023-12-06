package programmers.dp.숫자타자대회;

import java.util.Arrays;

public class Solution {

    static final int[][] cost = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    static int[][][] dp;
    static String target;

    private static int findMinCost(int left, int right, int depth) {
        if (depth == target.length()) {
            return 0;
        }

        if (dp[depth][left][right] != -1) {
            return dp[depth][left][right];
        }

        int result = Integer.MAX_VALUE;
        int num = target.charAt(depth) - '0';

        // 왼쪽 손가락으로 움직이기
        if (num != right) {
            result = Math.min(findMinCost(num, right, depth + 1) + cost[left][num], result);
        }

        // 오른쪽 손가락으로 움직이기
        if (num != left) {
            result = Math.min(findMinCost(left, num, depth + 1) + cost[right][num], result);
        }

        dp[depth][left][right] = result;

        return result;
    }

    public static int solution(String numbers) {
        int answer = 0;

        target = numbers;

        dp = new int[numbers.length() + 1][10][10];
        for (int i = 0; i < numbers.length() + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }

        answer = findMinCost(4, 6, 0);

        return answer;
    }

    public static void main(String[] args) {
        String numbers = "1756";

        System.out.println(solution(numbers));
    }
}
package programmers.simulation.당구연습;

import java.util.Arrays;

public class Solution {

    static int getDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int curLen = 0;
            int result = Integer.MAX_VALUE;

            // 동
            if (!(startY == targetY && startX <= targetX)) {
                curLen = getDistance(startX, startY, m + (m - targetX), targetY);
                result = Math.min(result, curLen);
            }

            // 서
            if (!(startY == targetY && startX >= targetX)) {
                curLen = getDistance(startX, startY, targetX * (-1), targetY);
                result = Math.min(result, curLen);
            }

            // 남
            if (!(startX == targetX && startY >= targetY)) {
                curLen = getDistance(startX, startY, targetX, targetY * (-1));
                result = Math.min(result, curLen);
            }

            // 북
            if (!(startX == targetX && startY <= targetY)) {
                curLen = getDistance(startX, startY, targetX, n + (n - targetY));
                result = Math.min(result, curLen);
            }

            answer[i] = result;
        }

        return answer;
    }

    public static void main(String[] args) {
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = { { 7, 7 }, { 2, 7 }, { 7, 3 } };

        System.out.println(Arrays.toString(solution(m, n, startX, startY, balls)));
    }
}
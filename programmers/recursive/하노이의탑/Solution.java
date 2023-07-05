package programmers.recursive.하노이의탑;

import java.util.Arrays;

public class Solution {

    static int[][] answer;
    static int index = 0;

    public static void hanoi(int n, int start, int dest, int waypoint) {
        if (n == 1) {
            answer[index++] = new int[] { start, dest };
        } else {
            hanoi(n - 1, start, waypoint, dest);

            answer[index++] = new int[] { start, dest };

            hanoi(n - 1, waypoint, dest, start);
        }
    }

    public static int[][] solution(int n) {
        answer = new int[(int) Math.pow(2, n) - 1][2];

        hanoi(n, 1, 3, 2);

        return answer;
    }

    public static void main(String[] args) {
        int n = 2;

        int[][] result = solution(n);
        for (int i = 0; i < (int) Math.pow(2, n) - 1; i++)
            System.out.println(Arrays.toString(result[i]));
    }
}
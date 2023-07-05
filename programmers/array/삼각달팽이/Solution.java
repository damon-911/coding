package programmers.array.삼각달팽이;

import java.util.*;

public class Solution {

    public static int[] solution(int n) {
        int[] answer = new int[(n * (n + 1)) / 2];
        int[][] nums = new int[n][n];

        int curX = -1;
        int curY = 0;
        int curNum = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    curX++;
                } else if (i % 3 == 1) {
                    curY++;
                } else if (i % 3 == 2) {
                    curX--;
                    curY--;
                }

                nums[curX][curY] = curNum++;
            }
        }

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0)
                    break;

                answer[k++] = nums[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(Arrays.toString(solution(n)));
    }
}
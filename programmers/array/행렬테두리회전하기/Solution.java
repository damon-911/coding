package programmers.array.행렬테두리회전하기;

import java.util.Arrays;

public class Solution {

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] board = new int[rows][columns];
        int value = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = value++;
            }
        }

        for (int count = 0; count < queries.length; count++) {
            int x1 = queries[count][0] - 1;
            int y1 = queries[count][1] - 1;
            int x2 = queries[count][2] - 1;
            int y2 = queries[count][3] - 1;

            int save = board[x1][y2];
            int min = save;

            // 숫자를 우로 이동 (상단)
            for (int i = y2 - 1; i >= y1; i--) {
                min = Math.min(min, board[x1][i]);
                board[x1][i + 1] = board[x1][i];
            }

            // 숫자를 위로 이동 (좌측)
            for (int i = x1 + 1; i <= x2; i++) {
                min = Math.min(min, board[i][y1]);
                board[i - 1][y1] = board[i][y1];
            }

            // 숫자를 좌로 이동 (하단)
            for (int i = y1 + 1; i <= y2; i++) {
                min = Math.min(min, board[x2][i]);
                board[x2][i - 1] = board[x2][i];
            }

            // 숫자를 아래로 이동 (우측)
            for (int i = x2 - 1; i >= x1; i--) {
                min = Math.min(min, board[i][y2]);
                board[i + 1][y2] = board[i][y2];
            }

            board[x1 + 1][y2] = save;
            answer[count] = min;
        }

        return answer;
    }

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {
                { 2, 2, 5, 4 },
                { 3, 3, 6, 6 },
                { 5, 1, 6, 3 }
        };

        System.out.println(Arrays.toString(solution(rows, columns, queries)));
    }
}
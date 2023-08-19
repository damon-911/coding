package programmers.array.바탕화면정리;

import java.util.Arrays;

public class Solution {

    public static int[] solution(String[] wallpaper) {
        // [0] : minX, [1] : minY, [2] : maxX, [3] : maxY
        int[] answer = new int[4];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        answer[2] = Integer.MIN_VALUE;
        answer[3] = Integer.MIN_VALUE;

        char[][] board = new char[wallpaper.length][wallpaper[0].length()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = wallpaper[i].charAt(j);
                if (board[i][j] == '#') {
                    answer[0] = Math.min(answer[0], i);
                    answer[1] = Math.min(answer[1], j);
                    answer[2] = Math.max(answer[2], i + 1);
                    answer[3] = Math.max(answer[3], j + 1);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] wallpaper = { ".#...", "..#..", "...#." };

        System.out.println(Arrays.toString(solution(wallpaper)));
    }
}
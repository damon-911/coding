package programmers.simulation.빛의경로사이클;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    // 아래 -> 오른 -> 위 -> 왼
    static final int[] MX = { 1, 0, -1, 0 };
    static final int[] MY = { 0, 1, 0, -1 };

    static int row, col;
    static boolean[][][] visited;

    static int light(String[] grid, int r, int c, int d) {
        int result = 0;

        while (true) {
            if (visited[r][c][d])
                break;

            result++;
            visited[r][c][d] = true;

            if (grid[r].charAt(c) == 'L')
                d = (d == 3) ? 0 : d + 1; // 좌회전
            else if (grid[r].charAt(c) == 'R')
                d = (d == 0) ? 3 : d - 1; // 우회전

            r = (r + MX[d] + row) % row;
            c = (c + MY[d] + col) % col;
        }

        return result;
    }

    public static int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();

        row = grid.length;
        col = grid[0].length();

        visited = new boolean[row][col][4];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d])
                        answer.add(light(grid, i, j, d));
                }
            }
        }

        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] grid = { "SL", "LR" };

        System.out.println(Arrays.toString(solution(grid)));
    }
}
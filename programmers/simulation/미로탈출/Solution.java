package programmers.simulation.미로탈출;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, -1, 0, 1 };

    static int row, col;
    static char[][] map;

    static int bfs(int[] pos, char target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { pos[0], pos[1], 0 });

        boolean[][] visited = new boolean[row][col];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int time = cur[2];

            visited[curX][curY] = true;

            if (map[curX][curY] == target)
                return time;

            for (int i = 0; i < 4; i++) {
                int tx = curX + MX[i];
                int ty = curY + MY[i];

                if (tx < 0 || ty < 0 || tx >= row || ty >= col || visited[tx][ty] || map[tx][ty] == 'X')
                    continue;

                visited[tx][ty] = true;
                queue.add(new int[] { tx, ty, time + 1 });
            }
        }

        return -1;
    }

    public static int solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();

        map = new char[row][col];

        int[] start = new int[2];
        int[] lever = new int[2];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = maps[i].charAt(j);

                if (map[i][j] == 'S')
                    start = new int[] { i, j };

                if (map[i][j] == 'L')
                    lever = new int[] { i, j };
            }
        }

        int result1 = bfs(start, 'L');
        int result2 = bfs(lever, 'E');

        if (result1 == -1 || result2 == -1)
            return -1;

        return result1 + result2;
    }

    public static void main(String[] args) {
        String[] maps = { "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE" };

        System.out.println(solution(maps));
    }
}
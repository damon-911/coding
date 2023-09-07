package programmers.simulation.무인도여행;

import java.util.*;

public class Solution {

    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, -1, 0, 1 };

    static int row, col;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> answer = new ArrayList<>();

    static int bfs(int x, int y) {
        int sum = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });

        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            sum += map[cx][cy];

            for (int i = 0; i < 4; i++) {
                int tx = cx + MX[i];
                int ty = cy + MY[i];

                if (tx < 0 || ty < 0 || tx >= row || ty >= col)
                    continue;

                if (!visited[tx][ty] && map[tx][ty] != -1) {
                    visited[tx][ty] = true;
                    queue.offer(new int[] { tx, ty });
                }
            }
        }

        return sum;
    }

    public static int[] solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();

        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = maps[i].charAt(j);
                if (c == 'X')
                    map[i][j] = -1;
                else
                    map[i][j] = c - '0';
            }
        }

        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && map[i][j] != -1)
                    answer.add(bfs(i, j));
            }
        }

        if (answer.size() == 0) {
            answer.add(-1);
        }

        Collections.sort(answer);

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] maps = { "X591X", "X1X5X", "X231X", "1XXX1" };

        System.out.println(Arrays.toString(solution(maps)));
    }
}
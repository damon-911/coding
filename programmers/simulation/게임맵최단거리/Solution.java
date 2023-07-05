package programmers.simulation.게임맵최단거리;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N, M;
    static int[][] visited;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = 1;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = p.x + MX[i];
                int ty = p.y + MY[i];

                if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
                    if (visited[tx][ty] == 0 && maps[tx][ty] == 1) {
                        queue.add(new Point(tx, ty));
                        visited[tx][ty] = visited[p.x][p.y] + 1;
                    }
                }
            }
        }
    }

    public static int solution(int[][] maps) {
        int answer = -1;

        N = maps.length;
        M = maps[0].length;

        visited = new int[N][M];

        bfs(maps);

        if (visited[N - 1][M - 1] != 0)
            answer = visited[N - 1][M - 1];

        return answer;
    }

    public static void main(String[] args) {
        int[][] maps = {
                { 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 0, 0, 1 }
        };

        System.out.println(solution(maps));
    }
}
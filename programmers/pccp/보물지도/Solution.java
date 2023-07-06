package programmers.pccp.보물지도;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M;
    static int[][] map;

    static class State {
        int x;
        int y;
        int canJump;
        int step;

        public State(int x, int y, int canJump, int step) {
            this.x = x;
            this.y = y;
            this.canJump = canJump;
            this.step = step;
        }
    }

    private static boolean isOut(int x, int y) {
        return x < 1 || x > N || y < 1 || y > M;
    }

    private static int move() {
        boolean[][][] isVisited = new boolean[N + 1][M + 1][2];
        isVisited[1][1][1] = true;

        Queue<State> q = new LinkedList<>();
        q.add(new State(1, 1, 1, 0));

        while (!q.isEmpty()) {
            State s = q.poll();

            if (s.x == N && s.y == M) {
                return s.step;
            }

            for (int d = 0; d < 4; d++) {
                int tx = s.x + MX[d];
                int ty = s.y + MY[d];

                if (isOut(tx, ty)) {
                    continue;
                }

                if (map[tx][ty] == 0) {
                    if (isVisited[tx][ty][s.canJump]) {
                        continue;
                    }
                    isVisited[tx][ty][s.canJump] = true;
                    q.add(new State(tx, ty, s.canJump, s.step + 1));
                }

                if (s.canJump != 1) {
                    continue;
                }

                int ttx = tx + MX[d];
                int tty = ty + MY[d];

                if (isOut(ttx, tty)) {
                    continue;
                }

                if (map[ttx][tty] == -1) {
                    continue;
                }

                if (isVisited[ttx][tty][0]) {
                    continue;
                }

                isVisited[ttx][tty][0] = true;
                q.add(new State(ttx, tty, 0, s.step + 1));
            }
        }

        return -1;
    }

    public static int solution(int n, int m, int[][] hole) {
        int answer = 0;
        N = n;
        M = m;

        map = new int[n + 1][m + 1];

        for (int[] h : hole) {
            map[h[0]][h[1]] = -1;
        }

        answer = move();

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int[][] hole = {
                { 2, 3 },
                { 3, 3 }
        };

        System.out.println(solution(n, m, hole));
    }
}
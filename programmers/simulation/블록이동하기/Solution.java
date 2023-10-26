package programmers.simulation.블록이동하기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };
    static final int[][] RX = { { -1, 0, -1, 0 }, { 0, 0, 1, 1 } };
    static final int[][] RY = { { 0, 0, 1, 1 }, { -1, 0, -1, 0 } };

    static int answer, N;
    static boolean[][][] visited;

    static class Robot {
        int x;
        int y;
        int dir;
        int count;

        Robot(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }

    static boolean canMove(int tx, int ty, int dir, int[][] board) {
        if (dir == 0) {
            if (tx < 0 || ty < 0 || tx >= N || ty >= N || ty + 1 >= N
                    || board[tx][ty] != 0 || board[tx][ty + 1] != 0) {
                return false;
            }
        } else {
            if (tx < 0 || ty < 0 || tx >= N || tx + 1 >= N || ty >= N
                    || board[tx][ty] != 0 || board[tx + 1][ty] != 0) {
                return false;
            }
        }
        return true;
    }

    static void bfs(int[][] board) {
        Queue<Robot> queue = new LinkedList<>();
        queue.add(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            if (cur.dir == 0 && cur.x == N - 1 && cur.y == N - 2) {
                answer = Math.min(answer, cur.count);
                continue;
            } else if (cur.dir == 1 && cur.x == N - 2 && cur.y == N - 1) {
                answer = Math.min(answer, cur.count);
                continue;
            }

            // 동서남북 이동
            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (!canMove(tx, ty, cur.dir, board)) {
                    continue;
                }

                if (!visited[cur.dir][tx][ty]) {
                    queue.add(new Robot(tx, ty, cur.dir, cur.count + 1));
                    visited[cur.dir][tx][ty] = true;
                }
            }

            // 회전
            for (int i = 0; i < 4; i++) {
                int tx = cur.x + RX[cur.dir][i];
                int ty = cur.y + RY[cur.dir][i];

                /* 가로 방향은 상하로 움직일 수 있으면 회전 가능 */
                int cx = cur.x + MX[i % 2];
                int cy = cur.y + MY[i % 2];

                if (cur.dir == 1) { // 세로 방향은 좌우로 움직일 수 있으면 회전 가능
                    cx = cur.x + MX[i < 2 ? i + 2 : i];
                    cy = cur.y + MY[i < 2 ? i + 2 : i];
                }

                int nowDir = cur.dir ^ 1;

                if (!canMove(tx, ty, nowDir, board) || !canMove(cx, cy, cur.dir, board))
                    continue;

                if (!visited[nowDir][tx][ty]) {
                    queue.add(new Robot(tx, ty, nowDir, cur.count + 1));
                    visited[nowDir][tx][ty] = true;
                }
            }
        }
    }

    public static int solution(int[][] board) {
        answer = Integer.MAX_VALUE;

        N = board.length;

        visited = new boolean[2][N + 1][N + 1];

        bfs(board);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 1, 0 },
                { 0, 1, 0, 1, 1 },
                { 1, 1, 0, 0, 1 },
                { 0, 0, 0, 0, 0 }
        };

        System.out.println(solution(board));
    }
}
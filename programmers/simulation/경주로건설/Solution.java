package programmers.simulation.경주로건설;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // 동 서 남 북
    static final int[] MX = { 0, 0, 1, -1 };
    static final int[] MY = { 1, -1, 0, 0 };

    static int N;
    static int[][][] visited;

    static class Node {
        int x, y, dir, cost;

        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }

    static int bfs(int[][] board) {
        int x = 0;
        int y = 0;
        int dir = -1;
        int cost = 0;
        int minCost = Integer.MAX_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, dir, cost));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                minCost = Math.min(minCost, cur.cost);
            }

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= N || board[tx][ty] == 1) {
                    continue;
                }

                int nextCost = cur.cost;
                if (cur.dir == -1 || cur.dir == i) {
                    nextCost += 100;
                } else {
                    nextCost += 600;
                }

                if (visited[tx][ty][i] == 0 || visited[tx][ty][i] >= nextCost) {
                    queue.add(new Node(tx, ty, i, nextCost));
                    visited[tx][ty][i] = nextCost;
                }
            }
        }

        return minCost;
    }

    public static int solution(int[][] board) {
        int answer = 0;

        N = board.length;

        visited = new int[N][N][4];

        answer = bfs(board);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };

        System.out.println(solution(board));
    }
}
package programmers.simulation.카드짝맞추기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static List<String> orders;
    static int answer;

    static class Point {
        int x;
        int y;
        int move;

        public Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static int[] check(int x, int y, int dir, int[][] copyBoard) {
        x += MX[dir];
        y += MY[dir];

        while (x >= 0 && y >= 0 && x < 4 && y < 4) {
            if (copyBoard[x][y] != 0) {
                return new int[] { x, y };
            }
            x += MX[dir];
            y += MY[dir];
        }

        return new int[] { x - MX[dir], y - MY[dir] };
    }

    static int bfs(int[] pos, int num, int[][] copyBoard) {
        int x = pos[0];
        int y = pos[1];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y, 0));

        boolean[][] visited = new boolean[4][4];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point next = queue.poll();
            int nextX = next.x;
            int nextY = next.y;
            int nextMove = next.move;

            if (copyBoard[nextX][nextY] == num) {
                pos[0] = next.x;
                pos[1] = next.y;
                return nextMove;
            }

            // 상하좌우 한 칸 이동
            for (int i = 0; i < 4; i++) {
                int tx = nextX + MX[i];
                int ty = nextY + MY[i];

                if (tx < 0 || ty < 0 || tx > 3 || ty > 3 || visited[tx][ty])
                    continue;

                visited[tx][ty] = true;
                queue.add(new Point(tx, ty, nextMove + 1));
            }

            // Ctrl 사용하여 상하좌우 여러 칸 이동
            for (int i = 0; i < 4; i++) {
                int[] result = check(nextX, nextY, i, copyBoard);
                int tx = result[0];
                int ty = result[1];

                if ((tx == x && ty == y) || visited[tx][ty])
                    continue;

                visited[tx][ty] = true;
                queue.add(new Point(tx, ty, nextMove + 1));
            }
        }
        return 0;
    }

    static void findCard(int[][] board, int r, int c) {
        for (String order : orders) {
            String[] cardOrder = order.split("");

            int totalMove = 0;
            int[] pos = new int[2];
            pos[0] = r;
            pos[1] = c;

            int[][] copyBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            for (String card : cardOrder) {
                int num = Integer.parseInt(card);

                // 첫 번째 카드 찾기
                totalMove += bfs(pos, num, copyBoard);
                copyBoard[pos[0]][pos[1]] = 0;
                totalMove += 1; // enter

                // 두 번째 카드 찾기
                totalMove += bfs(pos, num, copyBoard);
                copyBoard[pos[0]][pos[1]] = 0;
                totalMove += 1; // enter
            }

            answer = Math.min(answer, totalMove);
        }
    }

    static void permutation(String str, int depth, int[] card) {
        if (card.length == depth) {
            orders.add(str);
            return;
        }

        for (int i = 0; i < card.length; i++) {
            int num = card[i];
            if (!str.contains(String.valueOf(num))) {
                permutation(str + num, depth + 1, card);
            }
        }
    }

    public static int solution(int[][] board, int r, int c) {
        answer = Integer.MAX_VALUE;

        int cardNum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardNum++;
                }
            }
        }
        cardNum /= 2;

        int[] card = new int[cardNum];
        for (int i = 0; i < cardNum; i++) {
            card[i] = i + 1;
        }

        // 카드 조합 순서 구하기
        orders = new ArrayList<>();
        permutation("", 0, card);

        findCard(board, r, c);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 1, 0, 0, 3 },
                { 2, 0, 0, 0 },
                { 0, 0, 0, 2 },
                { 3, 0, 1, 0 }
        };
        int r = 1;
        int c = 0;

        System.out.println(solution(board, r, c));
    }
}
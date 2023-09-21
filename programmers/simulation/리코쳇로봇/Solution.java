package programmers.simulation.리코쳇로봇;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { 0, 0, 1, -1 };
    static final int[] MY = { 1, -1, 0, 0 };

    static int row, col;
    static char[][] map;
    static boolean[][] visited;

    static int[] getNext(int pos, int curX, int curY, int depth) {
        while (curX + MX[pos] >= 0 && curX + MX[pos] < row
                && curY + MY[pos] >= 0 && curY + MY[pos] < col
                && map[curX + MX[pos]][curY + MY[pos]] != 'D') {
            curX += MX[pos];
            curY += MY[pos];
        }

        return new int[] { curX, curY, depth + 1 };
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { startX, startY, 0 });
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int depth = cur[2];

            if (map[curX][curY] == 'G')
                return depth;

            for (int i = 0; i < 4; i++) {
                int[] next = getNext(i, curX, curY, depth);

                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }

        return -1;
    }

    public static int solution(String[] board) {
        int answer = 0;

        row = board.length;
        col = board[0].length();

        map = new char[row][col];
        visited = new boolean[row][col];

        int startX = 0;
        int startY = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = board[i].charAt(j);

                if (map[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }

        answer = bfs(startX, startY);

        return answer;
    }

    public static void main(String[] args) {
        String[] board = {
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D...."
        };

        System.out.println(solution(board));
    }
}
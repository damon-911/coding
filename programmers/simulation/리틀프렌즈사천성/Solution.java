package programmers.simulation.리틀프렌즈사천성;

import java.util.*;

public class Solution {

    static final int EMPTY = -1;
    static final int WALL = -2;

    static final int[] MX = { 0, 1, 0, -1 };
    static final int[] MY = { 1, 0, -1, 0 };

    static int[][] map;
    static int M, N;
    static Point[] start = new Point[26];

    static class Point {
        int x;
        int y;
        int dir;
        int cnt;

        public Point(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int calDir(int d, int diff) {
        int res = d + diff;
        if (res == 4)
            return 0;
        else if (res == -1)
            return 3;
        else
            return res;
    }

    static String bfs(int num) {
        StringBuilder result = new StringBuilder();
        Queue<Point> queue = new ArrayDeque<>();

        while (true) {
            boolean flag = true;

            // 모든 가능 카드에 대해 반복
            for (int c = 0; c < 26; c++) {
                if (!flag)
                    break;
                if (start[c] == null)
                    continue;

                queue.clear();
                boolean[][][] checked = new boolean[M][N][4];

                for (int d = 0; d < 4; d++) {
                    int tx = start[c].x + MX[d];
                    int ty = start[c].y + MY[d];

                    if (tx < 0 || ty < 0 || tx >= M || ty >= N || (map[tx][ty] != EMPTY && map[tx][ty] != c))
                        continue;

                    checked[tx][ty][d] = true;
                    queue.add(new Point(tx, ty, d, 0));
                }

                while (!queue.isEmpty()) {
                    Point cur = queue.poll();

                    // 마지막 지점이 시작 지점과 같을 경우
                    if (map[cur.x][cur.y] == c) {
                        // 시작과 끝을 빈 칸으로 만들어주기
                        map[start[c].x][start[c].y] = EMPTY;
                        map[cur.x][cur.y] = EMPTY;

                        // 후보에서 제외
                        start[c] = null;

                        // 결과에 더해줌
                        result.append((char) (c + 'A'));

                        // flag 변화
                        flag = false;
                        break;
                    }

                    // 좌로 꺾기, 정방향, 우로 꺾기
                    for (int dd = -1; dd <= 1; dd++) {
                        // 횟수 소진되었으면 더이상 꺾기 불가
                        if (cur.cnt == 1 && (dd == -1 || dd == 1))
                            continue;

                        int D = calDir(cur.dir, dd);

                        int tx = cur.x + MX[D];
                        int ty = cur.y + MY[D];

                        if (tx < 0 || ty < 0 || tx >= M || ty >= N || (map[tx][ty] != EMPTY && map[tx][ty] != c))
                            continue;

                        if (checked[tx][ty][D])
                            continue;

                        checked[tx][ty][D] = true;

                        if (cur.cnt == 1)
                            queue.add(new Point(tx, ty, D, 1));
                        else
                            queue.add(new Point(tx, ty, D, cur.dir == D ? 0 : 1));
                    }
                }
            }

            if (flag)
                break;
        }

        if (result.length() != num)
            return "IMPOSSIBLE";
        else
            return result.toString();
    }

    public static String solution(int m, int n, String[] board) {
        int num = 0;
        M = m;
        N = n;

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                char card = board[i].charAt(j);
                if (card >= 'A' && card <= 'Z') {
                    if (start[card - 'A'] == null) {
                        num++;
                        start[card - 'A'] = new Point(i, j, 0, 0);
                    }
                    map[i][j] = card - 'A';
                } else if (card == '.')
                    map[i][j] = EMPTY; // 빈칸
                else
                    map[i][j] = WALL; // 벽
            }
        }

        return bfs(num);
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        String[] board = { "DBA", "C*A", "CDB" };

        System.out.println(solution(m, n, board));
    }
}
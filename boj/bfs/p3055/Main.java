package boj.bfs.p3055;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0};
    static final int[] MY = { 0, 0, -1, 1};

    static int R, C;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;

    static class Point {
        int y;
        int x;
        char type;

        public Point(int y, int x, char type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }

        @Override
        public String toString() {
            return "[" +
                    "y=" + y +
                    ", x=" + x +
                    ", type=" + type +
                    ']';
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p3055/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point start = null;    // 시작점
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);

                if (map[r][c] == '*')
                    queue.add(new Point(r, c, '*'));
                else if (map[r][c] == 'S') {
                    start = new Point(r, c, 'S');
                }
            }
        }

        queue.add(start);

        boolean foundAnswer = false;
        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내옴
            Point p = queue.poll();

            // 2. 목적지인가? (고슴도치가 D에 도착)
            if (p.type == 'D') {
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            // 3. 연결된 곳을 순회 (상하좌우로 이동)
            for (int i = 0; i < 4; i++) {
                int ty = p.y + MY[i];
                int tx = p.x + MX[i];

                // 4. 갈 수 있는가? (공통 -> 맵 벗어나지 않고)
                if (0 <= ty && ty < R && 0 <= tx && tx < C) {
                    if (p.type == '.' || p.type == 'S') {
                        // 4. 갈 수 있는가? (고슴도치 -> '.', 'D')
                        if ((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
                            // 5. 체크인 (고슴도치 -> dp)
                            dp[ty][tx] = dp[p.y][p.x] + 1;

                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, map[ty][tx]));
                        }
                    }
                    else if (p.type == '*') {
                        // 4. 갈 수 있는가? (물 -> '.', 'S')
                        if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            // 5. 체크인 (물 -> map)
                            map[ty][tx] = '*';

                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }

        if (!foundAnswer)
            System.out.println("KAKTUS");
    }
}

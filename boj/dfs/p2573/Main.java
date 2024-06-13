package boj.dfs.p2573;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M;
    static int[][] map;
    static int result = 0;
    static Queue<Iceberg> queue = new LinkedList<>();

    static class Iceberg {
        int x;
        int y;

        public Iceberg(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs() {
        Queue<Iceberg> queue = new LinkedList<>();

        // 모든 빙하의 높이가 동시에 변할 수 있도록 설정
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    queue.offer(new Iceberg(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Iceberg cur = queue.poll();

            int seaNum = 0;

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= M) {
                    continue;
                }

                if (map[tx][ty] == 0 && !visited[tx][ty]) {
                    seaNum++;
                }
            }
            if (map[cur.x][cur.y] > seaNum) {
                map[cur.x][cur.y] -= seaNum;
            } else {
                map[cur.x][cur.y] = 0;
            }
        }
    }

    static void dfs(int curX, int curY, boolean[][] visited) {
        visited[curX][curY] = true;

        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx < 0 || ty < 0 || tx >= N || ty >= M) {
                continue;
            }

            if (map[tx][ty] != 0 && !visited[tx][ty]) {
                dfs(tx, ty, visited);
            }
        }
    }

    static int getIcebergNum() {
        boolean[][] visited = new boolean[N][M];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p2573/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = getIcebergNum();

        while (num < 2) {
            if (num == 0) {
                result = 0;
                break;
            }
            bfs();
            result++;
            num = getIcebergNum();
        }

        System.out.println(result);
    }
}

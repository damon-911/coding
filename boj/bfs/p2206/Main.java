package boj.bfs.p2206;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;

    static class Zone {
        int x;
        int y;
        int dis;
        boolean canBreak;

        public Zone(int x, int y, int dis, boolean canBreak) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.canBreak = canBreak;
        }
    }

    static void bfs() {
        Queue<Zone> queue = new LinkedList<>();
        queue.offer(new Zone(0, 0, 1, true));

        while (!queue.isEmpty()) {
            Zone z = queue.poll();

            if (z.x == N - 1 && z.y == M - 1) {
                System.out.println(z.dis);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int tx = z.x + MX[i];
                int ty = z.y + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= M)
                    continue;

                if (map[tx][ty] == '0') {
                    if (z.canBreak && !visited[tx][ty][0]) {
                        queue.add(new Zone(tx, ty, z.dis + 1, true));
                        visited[tx][ty][0] = true;
                    } else if (!z.canBreak && !visited[tx][ty][1]) {
                        queue.add(new Zone(tx, ty, z.dis + 1, false));
                        visited[tx][ty][1] = true;
                    }
                } else {
                    if (z.canBreak) {
                        queue.add(new Zone(tx, ty, z.dis + 1, false));
                        visited[tx][ty][1] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p2206/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        bfs();
    }
}

package boj.simulation.p2146;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int result = Integer.MAX_VALUE;

    static class Island {
        int x;
        int y;
        int cnt;

        Island(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static void findMinLength(int idx) {
        boolean[][] check = new boolean[N][N];
        Queue<Island> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == idx) {
                    queue.add(new Island(i, j, 0));
                    check[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Island cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N) {
                    continue;
                }

                // 이동한 곳이 다른 섬이었을 경우
                if (map[tx][ty] != 0 && map[tx][ty] != idx) {
                    if (cur.cnt != 0) {
                        result = Math.min(result, cur.cnt);
                    }
                } else {
                    // 이동한 곳이 바다이고 체크되지 않았을 경우
                    if (map[tx][ty] == 0 && !check[tx][ty]) {
                        check[tx][ty] = true;
                        queue.add(new Island(tx, ty, cur.cnt + 1));
                    }
                }
            }
        }
    }

    static void findIsland(int curX, int curY, int idx) {
        map[curX][curY] = idx;
        visited[curX][curY] = true;

        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == 0 || visited[tx][ty]) {
                continue;
            }

            findIsland(tx, ty, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p2146/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];

        int idx = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    findIsland(i, j, idx);
                    idx++;
                }
            }
        }

        for (int i = 2; i < idx; i++) {
            findMinLength(i);
        }

        System.out.println(result);
    }
}
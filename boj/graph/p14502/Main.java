package boj.graph.p14502;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MX[] = { 0, 0, -1, 1 };
    static final int MY[] = { -1, 1, 0, 0 };

    static int N, M;
    static int[][] map;
    static int maxSafeZone = Integer.MIN_VALUE;

    private static void getSafeZone(int[][] copyMap) {
        int safeZone = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    safeZone++;
                }
            }
        }

        maxSafeZone = Math.max(maxSafeZone, safeZone);
    }

    static void virus() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    queue.add(new int[] { i, j });
                }
            }
        }

        int copyMap[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int tx = x + MX[i];
                int ty = y + MY[i];

                if (0 <= tx && tx < N && 0 <= ty && ty < M) {
                    if (copyMap[tx][ty] == 0) {
                        queue.add(new int[] { tx, ty });
                        copyMap[tx][ty] = 2;
                    }
                }
            }
        }

        getSafeZone(copyMap);
    }

    static void installWall(int wallCnt) {
        // 벽이 3개 설치 된 경우, bfs 탐색 시작
        if (wallCnt == 3) {
            virus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    installWall(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p14502/input.txt"));
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

        installWall(0);

        System.out.println(maxSafeZone);
    }
}

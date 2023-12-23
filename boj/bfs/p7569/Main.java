package boj.bfs.p7569;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1, 0, 0 };
    static final int[] MZ = { 0, 0, 0, 0, -1, 1 };

    static int N, M, H;
    static int[][][] box;
    static Queue<int[]> queue = new LinkedList<>();

    static boolean checkZero() {
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (box[i][j][k] == 0)
                        return true;
                }
            }
        }
        return false;
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 6; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];
                int tz = cur[2] + MZ[i];

                if (tx < 0 || ty < 0 || tz < 0 || tx >= N || ty >= M || tz >= H) {
                    continue;
                }

                if (box[tx][ty][tz] == 0) {
                    box[tx][ty][tz] = box[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[] { tx, ty, tz });
                }
            }
        }

        int max = Integer.MIN_VALUE;

        if (checkZero()) {
            return -1;
        } else {
            for (int k = 0; k < H; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (max < box[i][j][k]) {
                            max = box[i][j][k];
                        }
                    }
                }
            }
            return max - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p7569/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[N][M][H];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) {
                        queue.add(new int[] { i, j, k });
                    }
                }
            }
        }

        System.out.println(bfs());
    }
}

package boj.bfs.p7576;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N, M;
    static int result = -1;
    static int[][] box;
    static Queue<int[]> queue = new LinkedList<>();

    private static boolean checkZero() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = temp[0] + MX[i];
                int ty = temp[1] + MY[i];

                if (tx < 0 || tx >= N || ty < 0 || ty >= M)
                    continue;

                if (box[tx][ty] == 0) {
                    box[tx][ty] = box[temp[0]][temp[1]] + 1;
                    queue.offer(new int[] { tx, ty });
                }
            }
        }

        int max = Integer.MIN_VALUE;

        if (checkZero()) {
            return -1;
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (max < box[i][j]) {
                        max = box[i][j];
                    }
                }
            }
            return max - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p7576/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1)
                    queue.offer(new int[] { i, j });
            }
        }

        System.out.println(bfs());
    }
}

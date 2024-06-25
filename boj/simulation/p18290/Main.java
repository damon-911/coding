package boj.simulation.p18290;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    static boolean check(int curX, int curY) {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx >= 0 && ty >= 0 && tx < N && ty < M) {
                if (visited[tx][ty]) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    static void findMax(int curX, int curY, int depth, int total) {
        if (depth == K) {
            max = Math.max(max, total);
            return;
        }

        for (int i = curX; i < N; i++) {
            for (int j = curY; j < M; j++) {
                if (!visited[i][j]) {
                    if (check(i, j)) {
                        visited[i][j] = true;
                        findMax(curX, curY, depth + 1, total + map[i][j]);
                        visited[i][j] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p18290/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMax(0, 0, 0, 0);

        System.out.println(max);
    }
}

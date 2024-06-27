package boj.dfs.p1520;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int M, N;
    static int[][] map, dp;

    static int dfs(int curX, int curY) {
        if (curX == M - 1 && curY == N - 1) {
            return 1;
        }

        if (dp[curX][curY] != -1) {
            return dp[curX][curY];
        }

        dp[curX][curY] = 0;

        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx < 0 || ty < 0 || tx >= M || ty >= N) {
                continue;
            }

            if (map[tx][ty] < map[curX][curY]) {
                dp[curX][curY] += dfs(tx, ty);
            }
        }

        return dp[curX][curY];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1520/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}

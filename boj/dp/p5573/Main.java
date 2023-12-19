package boj.dp.p5573;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int H, W, N;
    static int[][] map, dp;

    static void followRoute() {
        int x = 1;
        int y = 1;
        while (true) {
            if (map[x][y] == 1)
                y++;
            else
                x++;

            if (x > H || y > W)
                break;
        }

        System.out.println(x + " " + y);
    }

    static void findRoute() {
        dp = new int[H + 1][W + 1]; // dp[i][j] : (N - 1)번의 산책동안 (i, j)에 몇 번 지나갔는지 체크
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = N - 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j] / 2 + dp[i][j - 1] / 2;

                if (dp[i - 1][j] % 2 == 1 && map[i - 1][j] == 0) {
                    dp[i][j]++;
                }

                if (dp[i][j - 1] % 2 == 1 && map[i][j - 1] == 1) {
                    dp[i][j]++;
                }
            }
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (dp[i][j] % 2 == 0)
                    continue;

                map[i][j] = 1 - map[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p5573/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[H + 1][W + 1];

        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findRoute();
        followRoute();
    }
}
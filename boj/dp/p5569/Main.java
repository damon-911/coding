package boj.dp.p5569;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int W, H;
    static int[][][][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p5569/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dp = new int[W + 1][H + 1][2][2]; // 세번째 인덱스 : 방향, 네번째 인덱스 : 방향 전환 여부(0 -> false, 1 -> true)

        for (int i = 1; i <= W; i++) {
            dp[i][1][0][0] = 1;
        }

        for (int i = 1; i <= H; i++) {
            dp[1][i][1][0] = 1;
        }

        for (int i = 2; i <= W; i++) {
            for (int j = 2; j <= H; j++) {
                dp[i][j][1][0] = (dp[i][j - 1][1][1] + dp[i][j - 1][1][0]) % 100000;
                dp[i][j][1][1] = dp[i][j - 1][0][0] % 100000;
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % 100000;
                dp[i][j][0][1] = dp[i - 1][j][1][0] % 100000;
            }
        }

        int result = (dp[W][H][0][0] + dp[W][H][0][1] + dp[W][H][1][0] + dp[W][H][1][1]) % 100000;

        System.out.println(result);
    }
}
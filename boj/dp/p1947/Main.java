package boj.dp.p1947;

import java.io.*;

public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p1947/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[1_000_001];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        // a가 b에게 선물 주었다고 가정
        // 1) b도 a에게 선물 주었을 경우 : dp[N - 2] (N명 중에 2명은 확정)
        // 2) b도 a에게 선물 주었을 경우 : dp[N - 1] (N명 중에 1명만 확정)
        for (int i = 3; i <= N; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % MOD;
        }

        System.out.println(dp[N]);
    }
}
package boj.dp.p10422;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p10422/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] dp = new long[5001];
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= 5000; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] += (dp[j] * dp[i - j - 2]);
                dp[i] %= 1_000_000_007L;
            }
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int L = Integer.parseInt(br.readLine());
            System.out.println(dp[L]);
        }
    }
}
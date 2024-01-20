package boj.dp.p2294;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p2294/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 1; i <= n; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = coin; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        if (dp[k] == Integer.MAX_VALUE - 1)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
    }
}
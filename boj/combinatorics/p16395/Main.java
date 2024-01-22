package boj.combinatorics.p16395;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] dp;

    static int pascal(int n, int k) {
        if (n == k || k == 0)
            return dp[n][k] = 1;
        else if (dp[n][k] > 0)
            return dp[n][k];
        else
            return dp[n][k] = pascal(n - 1, k - 1) + pascal(n - 1, k);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p16395/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][K];

        System.out.println(pascal(N - 1, K - 1));
    }
}
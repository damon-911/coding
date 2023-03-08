package boj.combinatorics.p1010;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M;
    static int[][] dp;

    // 조합 공식 이용
    static int comb(int n, int r) {
        if (dp[n][r] > 0)
            return dp[n][r];

        if (n == r || r == 0)
            return dp[n][r] = 1;

        return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p1010/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        dp = new int[30][30];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(comb(M, N));
        }
    }
}
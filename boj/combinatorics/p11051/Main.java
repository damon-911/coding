package boj.combinatorics.p11051;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] dp;

    static int newPascal(int a, int b) {
        if (a == b || b == 0)
            return dp[a][b] = 1;
        else if (dp[a][b] > 0)
            return dp[a][b];
        else
            return dp[a][b] = (newPascal(a - 1, b - 1) + newPascal(a - 1, b)) % 10007;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p11051/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        System.out.println(newPascal(N, K));
    }
}
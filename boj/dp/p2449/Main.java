package boj.dp.p2449;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] bulb;
    static int[][] dp;

    static int findMin(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int tmp = -1;
        dp[left][right] = Integer.MAX_VALUE;
        for (int i = left; i < right; ++i) {
            tmp = (bulb[left] == bulb[i + 1]) ? 0 : 1;
            dp[left][right] = Math.min(dp[left][right], findMin(left, i) + findMin(i + 1, right) + tmp);
        }

        return dp[left][right];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p2449/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bulb = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bulb[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findMin(0, N - 1));
    }
}
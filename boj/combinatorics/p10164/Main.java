package boj.combinatorics.p10164;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;

    static long findRoute(int curX, int curY, int endX, int endY) {
        if (dp[curX][curY] != 0) {
            return dp[curX][curY];
        }

        if (curX == endX && curY == endY) {
            return 1;
        }

        // 아래쪽으로 가는 경우
        if (curX < endX && curY <= endY) {
            dp[curX][curY] += findRoute(curX + 1, curY, endX, endY);
        }

        // 오른쪽으로 가는 경우
        if (curX <= endX && curY < endY) {
            dp[curX][curY] += findRoute(curX, curY + 1, endX, endY);
        }

        return dp[curX][curY];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p10164/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new long[N][M];

        int rowK = K / M;
        int colK = K % M - 1;
        if (K % M == 0) {
            rowK -= 1;
            colK = M - 1;
        }

        if (K != 0) {
            System.out.println(findRoute(0, 0, rowK, colK) * findRoute(rowK, colK, N - 1, M - 1));
        } else {
            System.out.println(findRoute(0, 0, N - 1, M - 1));
        }
    }
}
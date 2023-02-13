package boj.dp.p1915;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp;
    static long maxSize;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p1915/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];

        maxSize = 0;

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();

            for (int j = 1; j <= M; j++) {
                int temp = input.charAt(j - 1) - '0';

                // 해당 숫자가 1일 경우 왼쪽 대각선 방향, 왼쪽 방향, 위쪽 방향 중 가장 작은 값에 1을 더한 값을 dp[i][j]에 저장
                // 만약 세 가지 방향 중 0이 존재한다면 가능한 최대 사각형은 1 x 1 이고, 세 가지 방향 중 가장 작은 값이 1 이라면 가능한 최대 사각형은 2 x 2 이다
                if (temp == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }

        System.out.println(maxSize * maxSize);
    }
}

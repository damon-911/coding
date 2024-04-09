package boj.dp.p1757;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] distance;
    static int[][] dp; // [시간][지침지수] : 갈 수 있는 최대 거리

    static void run(int cur, int move) {
        // cur분에 쉬었을 경우
        dp[cur][0] = dp[cur - 1][0];

        // cur분에 달렸을 경우
        for (int i = 1; i <= M; i++) {
            dp[cur][i] = dp[cur - 1][i - 1] + move;
        }

        // 지침지수가 0으로 끝났을 경우에 달린 거리의 최댓값 구하기
        for (int i = 1; i <= M && cur > i; i++) {
            // dp[cur - i][i] : 지침지수가 i일 때, i턴 쉴 경우 -> dp[cur][0]
            dp[cur][0] = Math.max(dp[cur][0], dp[cur - i][i]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p1757/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            run(i, distance[i]);
        }

        System.out.println(dp[N][0]);
    }
}
package boj.dp.p7579;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_MEMORY = 10001;
    static int N, M, minCost;
    static App[] apps;
    static int[][] dp;  // dp[i][j] : i번째까지의 앱을 사용할 때 j의 비용으로 확보 가능한 최대 메모리 크기

    static class App {
        int memory;
        int cost;

        public App(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }

    static void findMinCost() {
        minCost = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int curMem = apps[i].memory;
            int curCost = apps[i].cost;

            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= curCost)
                        dp[i][j] = curMem;
                }
                else {
                    if (j >= curCost)
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curCost] + curMem);
                    else
                        dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M)
                    minCost = Math.min(minCost, j);
            }
        }

        System.out.println(minCost);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p7579/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        apps = new App[N];
        dp = new int[N][MAX_MEMORY];

        StringTokenizer memories = new StringTokenizer(br.readLine());
        StringTokenizer costs = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            apps[i] = new App(Integer.parseInt(memories.nextToken()), Integer.parseInt(costs.nextToken()));
        }

        findMinCost();
    }
}

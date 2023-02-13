package boj.graph.p11404;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] costs;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11404/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        // 자기 자신은 0으로 채우고 나머지는 Integer.MAX_VALUE로 채운다.
        costs = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    costs[i][j] = 0;
                else
                    costs[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (costs[A][B] > C)
                costs[A][B] = C;
        }

        // for 문을 3번 돌려서 모든 정점에서 모든 정점으로 가는 최단거리를 구한다 (전체 쌍 최단 경로)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if ((i != j) && (costs[i][k] != Integer.MAX_VALUE) && (costs[k][j] != Integer.MAX_VALUE)) {
                        if (costs[i][j] > costs[i][k] + costs[k][j]) {
                            costs[i][j] = costs[i][k] + costs[k][j];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (costs[i][j] == Integer.MAX_VALUE)
                    System.out.print(0 + " ");
                else
                    System.out.print(costs[i][j] + " ");
            }
            System.out.println();
        }
    }
}

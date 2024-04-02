package boj.graph.p21278;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 100_001;

    static int N, M;
    static int[][] dis;

    static int sum(int v1, int v2) {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (i == v1 || i == v2)
                continue;
            result += Math.min(dis[v1][i], dis[v2][i]);
        }
        return result * 2; // 왕복으로 계산
    }

    static void choose() {
        int v1 = 0;
        int v2 = 0;
        int min = INF;

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int cur = sum(i, j);

                if (min > cur) {
                    v1 = i;
                    v2 = j;
                    min = cur;
                }
            }
        }

        System.out.println(v1 + " " + v2 + " " + min);
    }

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    if (dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        dis[j][i] = dis[i][k] + dis[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p21278/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dis = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    dis[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            dis[A][B] = 1;
            dis[B][A] = 1;
        }

        floyd();

        choose();
    }
}
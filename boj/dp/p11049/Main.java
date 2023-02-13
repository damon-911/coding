package boj.dp.p11049;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Matrix[] mat;
    static int[][] dp;

    static class Matrix {
        int r;
        int c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p11049/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        mat = new Matrix[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            mat[i] = new Matrix(r, c);
        }

        dp = new int[N + 1][N + 1];
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 점화식 이용 -> D[j][j + i - 1] = Min(D[j][k] + D[k + 1][j + i - 1] + r[j] * c[k] * c[j + i - 1])
                // j <= k < j + i - 1 <= N
                if (j + i - 1 <= N) {
                    int min = Integer.MAX_VALUE;
                    for (int k = j; k < j + i - 1; k++) {
                        int temp = dp[j][k] + dp[k + 1][j + i - 1] + mat[j].r * mat[k].c * mat[j + i - 1].c;
                        if (min > temp)
                            min = temp;
                    }

                    // 해당 구간의 곱셈 연산 횟수 최솟값을 넣는다
                    dp[j][j + i - 1] = min;
                }
            }
        }

        System.out.println(dp[1][N]);
    }
}

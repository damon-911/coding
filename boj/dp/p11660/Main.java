package boj.dp.p11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] sums;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p11660/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sums = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());

            System.out.println(sums[X2][Y2] - sums[X1 - 1][Y2] - sums[X2][Y1 - 1] + sums[X1 - 1][Y1 - 1]);
        }
    }
}

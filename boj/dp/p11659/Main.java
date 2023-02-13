package boj.dp.p11659;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] sums;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p11659/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sums[i] += sums[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            System.out.println(sums[B] - sums[A - 1]);
        }
    }
}

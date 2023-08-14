package boj.combinatorics.p13251;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] rocks;
    static double result;

    static void comb(int color) {
        if (rocks[color] >= K) {
            int total = N;
            int count = K;
            double temp = 1;
            while (count > 0) {
                temp *= (double) rocks[color]-- / total--;
                count--;
            }
            result += temp;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p13251/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        rocks = new int[M];

        N = 0;
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            rocks[i] = Integer.parseInt(st.nextToken());
            N += rocks[i];
        }

        K = Integer.parseInt(br.readLine());

        if (M == 1 || K == 1)
            System.out.println(1.0);
        else {
            for (int i = 0; i < M; i++) {
                comb(i);
            }
            System.out.println(result);
        }
    }
}
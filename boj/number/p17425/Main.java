package boj.number.p17425;

import java.io.*;
import java.util.Arrays;

public class Main {

    static final int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p17425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        long[] fx = new long[MAX + 1];
        long[] gx = new long[MAX + 1];

        // 1은 모든 수의 약수
        Arrays.fill(fx, 1);

        for (int i = 2; i <= MAX; i++) {
            for (int j = 1; i * j <= MAX; j++) {
                fx[i * j] += i;
            }
        }

        for (int i = 1; i <= MAX; i++) {
            gx[i] += gx[i - 1] + fx[i];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(gx[N]).append("\n");
        }

        System.out.print(sb);
    }
}

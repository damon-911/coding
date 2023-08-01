package boj.number.p2824;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1000000000;
    static int N, M;
    static int[] A, B;

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    private static void findGCD() {
        long result = 1;
        boolean outOfRange = false;

        for (int i = 0; i < N; i++) {
            if (A[i] == 1)
                continue;

            for (int j = 0; j < M; j++) {
                if (B[j] == 1)
                    continue;

                long gcd = gcd(A[i], B[j]);
                result *= gcd;

                if (result >= MOD)
                    outOfRange = true;
                result %= MOD;

                // result에 이미 gcd를 곱했으니 둘 다 gcd로 나눠줌
                A[i] /= gcd;
                B[j] /= gcd;
            }
        }

        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(result);

        if (str.length() < 9 && outOfRange) {
            while (sb.length() + str.length() < 9) {
                sb.append("0");
            }
        }
        sb.append(str);

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2824/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        findGCD();
    }
}
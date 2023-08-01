package boj.number.p2725;

import java.io.*;

public class Main {

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2725/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 3;

        for (int i = 2; i <= 1000; i++) {
            int count = 0;
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1)
                    count++;
            }
            dp[i] = dp[i - 1] + 2 * count;
        }

        for (int i = 0; i < C; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
package boj.dp.p2011;

import java.io.*;

public class Main {

    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p2011/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] num = br.readLine().toCharArray();

        if (num[0] == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[num.length + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= num.length; i++) {
            int prev = num[i - 2] - '0';
            int now = num[i - 1] - '0';
            int result = prev * 10 + now;

            // now가 0이라면 그 다음 차례에서는 해당 자릿수를 사용하지 못함
            if (now == 0) {
                if (0 < result && result <= 26) {
                    dp[i] = dp[i - 2] % MOD;
                } else {
                    break;
                }
            } else {
                // 두 자리 수로 사용
                if (9 < result && result <= 26) {
                    dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
                } else {
                    dp[i] = dp[i - 1] % MOD;
                }
            }
        }

        System.out.println(dp[num.length]);
    }
}

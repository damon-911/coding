package boj.dp.p5582;

import java.io.*;

public class Main {

    static String str1, str2;
    static int[][] dp;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p5582/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();

        dp = new int[str1.length() + 1][str2.length() + 1];
        max = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // 두 str를 비교하면서 같은 문자가 나올 경우 (왼쪽 대각선 방향 값 + 1) 을 취한다.
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(max);
    }
}

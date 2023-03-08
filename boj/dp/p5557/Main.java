package boj.dp.p5557;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static long[][] dp;

    static long findEquation(int pos, int sum) {
        // sum의 범위는 0 이상 20 이하
        if (sum < 0 || sum > 20)
            return 0;

        // 마지막 수와 지금까지의 sum과 비교
        if (pos == N - 2)
            return (sum == nums[pos + 1]) ? 1 : 0;

        // 값이 이미 있다면 바로 값 리턴
        if (dp[pos][sum] != -1)
            return dp[pos][sum];

        dp[pos][sum] = 0;

        return dp[pos][sum] += findEquation(pos + 1, sum + nums[pos + 1]) + findEquation(pos + 1, sum - nums[pos + 1]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p5557/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 수의 범위 : 0 ~ 20
        dp = new long[N][21];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(findEquation(0, nums[0]));
    }
}
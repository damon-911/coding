package boj.combinatorics.p15824;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p15824/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] nums = new long[N + 1];

        long[] pows = new long[N + 1];
        pows[0] = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
            pows[i] = (pows[i - 1] * 2) % MOD;
        }

        Arrays.sort(nums);

        long result = 0;

        // (i번째 값이 최대가 될 때, 모든 조합으로 나올 수 있는 최댓값) - (i번째 값이 최소가 될 때, 모든 조합으로 나올 수 있는 최솟값)
        // 1 ~ (i-1) -> (i-1)개 / (i+1) ~ N -> (N-i)개
        // (2^(i-1) * nums[i]) - (2^(n-i) * nums[i]) = (2^(i-1) - 2^(n-i)) * nums[i]
        for (int i = 1; i <= N; i++) {
            result += (pows[i - 1] - pows[N - i]) * nums[i];
            result %= MOD;
        }

        System.out.println(result);
    }
}
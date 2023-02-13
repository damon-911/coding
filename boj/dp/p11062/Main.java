package boj.dp.p11062;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p11062/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            nums = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

        }
    }
}

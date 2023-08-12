package boj.dp.p10714;

import java.io.*;

public class Main {

    static int N;
    static int[] cakes;
    static long[][] dp;

    static int checkLeft(int id) {
        return (id + N - 1) % N;
    }

    static int checkRight(int id) {
        return (id + 1) % N;
    }

    static long joi(int left, int right) {
        if (checkRight(right) == left) { // 가장 끝 조각까지 왔다면
            return dp[left][right] = 0;
        }

        if (dp[left][right] != 0) { // 이미 값이 있다면
            return dp[left][right];
        }

        // 왼족 조각을 선택했을 때와 오른족 조각을 선택했을 때를 비교하여 값이 큰 것을 선택
        long chooseLeft = cakes[checkLeft(left)] + ioi(checkLeft(left), right);
        long chooseRight = cakes[checkRight(right)] + ioi(left, checkRight(right));

        return dp[left][right] = Math.max(chooseLeft, chooseRight);
    }

    static long ioi(int left, int right) {
        if (checkRight(right) == left) { // 가장 끝 조각까지 왔다면
            return 0;
        }

        if (cakes[checkLeft(left)] > cakes[checkRight(right)]) { // 왼쪽 조각이 더 크면 왼쪽 조각 선택
            return joi(checkLeft(left), right);
        } else { // 아니라면 오른쪽 조각 선택
            return joi(left, checkRight(right));
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p10714/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cakes = new int[N];
        dp = new long[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            cakes[i] = Integer.parseInt(br.readLine());
        }

        long result = 0;

        for (int i = 0; i < N; i++) {
            result = Math.max(result, cakes[i] + ioi(i, i));
        }

        System.out.println(result);
    }
}

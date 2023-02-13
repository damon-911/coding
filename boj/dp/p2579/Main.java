package boj.dp.p2579;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] score;
    static int[] max;

    static int findMaxScore(int level) {
        if (level != 0 && max[level] == 0) {
            // 두 계단 전에 위치하거나 세 계단 전에 위치했다 두 계단 올라온 경우를 비교해서 큰 값을 취한다
            max[level] = Math.max(findMaxScore(level - 2), findMaxScore(level - 3) + score[level - 1]) + score[level];
        }

        return max[level];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p2579/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        score = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
        }

        max = new int[N + 1];
        max[1] = score[1];

        if (N > 1)
            max[2] = max[1] + score[2];

        for (int i = 3; i <= N; i++) {
            max[i] = Math.max(findMaxScore(i - 2), findMaxScore(i - 3) + score[i - 1]) + score[i];
        }

        System.out.println(max[N]);
    }
}

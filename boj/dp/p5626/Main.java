package boj.dp.p5626;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] height;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p5626/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        height = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }


    }
}

package boj.dp.p2618;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, W;
    static char[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p2618/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        map = new char[N + 1][N + 1];
        map[1][1] = '1';
        map[N][N] = '2';

        for (int i = 0; i < W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = '*';
        }
    }
}

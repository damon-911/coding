package boj.dfs.p1405;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 0, 0, 1, -1 };
    static final int[] MY = { 1, -1, 0, 0 };

    static int N;
    static double[] percent;
    static boolean[][] visited;
    static double result = 0;

    static void dfs(int x, int y, int depth, double total) {
        if (depth == N) {
            result += total;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx >= 0 && ty >= 0 && tx < 30 && ty < 30) {
                if (!visited[tx][ty]) {
                    visited[tx][ty] = true;
                    dfs(tx, ty, depth + 1, total * percent[i]);
                    visited[tx][ty] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1405/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        percent = new double[4];
        for (int i = 0; i < 4; i++) {
            percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        visited = new boolean[30][30]; // 시작점 : (15, 15)
        visited[15][15] = true;

        dfs(15, 15, 0, 1);

        System.out.println(result);
    }
}
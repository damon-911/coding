package boj.simulation.p14500;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] MX = { -1, 1, 0, 0 };
    static int[] MY = { 0, 0, -1, 1 };

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int max = 0;

    static void dfs(int x, int y, int depth, int sum) {
        if (depth >= 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || ty < 0 || tx >= N || ty >= M || visited[tx][ty]) {
                continue;
            }

            // ㅗ 모양 테트로미노
            if (depth == 2) {
                visited[tx][ty] = true;
                dfs(x, y, depth + 1, sum + board[tx][ty]);
                visited[tx][ty] = false;
            }

            visited[tx][ty] = true;
            dfs(tx, ty, depth + 1, sum + board[tx][ty]);
            visited[tx][ty] = false;
        }
    }

    static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p14500/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMax();

        System.out.println(max);
    }
}

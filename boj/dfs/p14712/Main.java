package boj.dfs.p14712;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] visited;
    static int result = 0;

    static boolean check(int depth) {
        if (depth < 4) {
            return true;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (visited[i][j] && visited[i][j + 1] && visited[i + 1][j] && visited[i + 1][j + 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void dfs(int depth, int cur) {
        result += check(depth) ? 1 : 0;

        if (depth == N * M) {
            return;
        }

        for (int i = cur; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if (!visited[r][c]) {
                visited[r][c] = true;
                dfs(depth + 1, i + 1);
                visited[r][c] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p14712/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];

        dfs(0, 0);

        System.out.println(result);
    }
}
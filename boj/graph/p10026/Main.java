package boj.graph.p10026;

import java.io.*;

public class Main {

    static final int MX[] = { 0, 0, -1, 1 };
    static final int MY[] = { -1, 1, 0, 0 };

    static int N;
    static char[][] board;
    static boolean visited[][];

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        char target = board[x][y];

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || ty < 0 || tx > N || ty > N) {
                continue;
            }

            if (!visited[tx][ty] && board[tx][ty] == target) {
                dfs(tx, ty);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p10026/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new char[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        // 정상인 경우
        int normal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    normal++;
                }
            }
        }

        visited = new boolean[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // G를 R로 바꿔준다.
                if (board[i][j] == 'G') {
                    board[i][j] = 'R';
                }
            }
        }

        // 적록색약인 경우
        int abnormal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    abnormal++;
                }
            }
        }

        System.out.println(normal + " " + abnormal);
    }
}

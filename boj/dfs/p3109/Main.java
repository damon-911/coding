package boj.dfs.p3109;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // 오른쪽 대각선 위 -> 오른쪽 -> 오른쪽 대각선 아래
    static final int[] MX = { -1, 0, 1 };
    static final int[] MY = { 1, 1, 1 };

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean flag;
    static int result = 0;

    static void dfs(int row, int col) {
        if (col == C - 1) {
            result++;
            visited[row][col] = true;
            flag = true;
            return;
        }

        visited[row][col] = true;

        for (int i = 0; i < 3; i++) {
            int tx = row + MX[i];
            int ty = col + MY[i];

            if (flag) {
                break;
            }

            if (tx < 0 || ty < 0 || tx >= R || ty >= C || visited[tx][ty] || map[tx][ty] == 'x') {
                continue;
            }

            dfs(tx, ty);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p3109/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            flag = false;
            dfs(i, 0);
        }

        System.out.println(result);
    }
}

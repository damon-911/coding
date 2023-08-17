package boj.dfs.p1987;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int R, C;
    static int result = 0;
    static String[][] board;

    static void dfs(int x, int y, int depth, String path) {
        if (path.contains(board[x][y])) {
            result = Math.max(result, depth);
            return;
        }

        path += board[x][y];

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || ty < 0 || tx >= R || ty >= C)
                continue;

            dfs(tx, ty, depth + 1, path);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1987/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new String[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = String.valueOf(str.charAt(j));
            }
        }

        dfs(0, 0, 0, "");

        System.out.println(result);
    }
}

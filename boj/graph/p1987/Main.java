package boj.graph.p1987;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int R, C;
    static int result = 0;
    static char[][] board;
    static Set<Character> visitedSet = new HashSet<>();

    static void dfs(int x, int y, int depth) {
        result = Math.max(result, depth);

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || ty < 0 || tx >= R || ty >= C || visitedSet.contains(board[tx][ty]))
                continue;

            visitedSet.add(board[tx][ty]);
            dfs(tx, ty, depth + 1);
            visitedSet.remove(board[tx][ty]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1987/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        visitedSet.add(board[0][0]);
        dfs(0, 0, 1);

        System.out.println(result);
    }
}
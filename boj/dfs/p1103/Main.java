package boj.dfs.p1103;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N, M;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static boolean cycleFlag;
    static int maxMove;

    static void dfs(int x, int y, int move) {
        // 1. 체크인
        visited[x][y] = true;
        dp[x][y] = move;

        // maxMove 최신화
        maxMove = Math.max(maxMove, move);

        // 2. 연결된 곳을 순회
        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i] * (board[x][y] - '0');
            int ty = y + MY[i] * (board[x][y] - '0');

            // 3. 갈 수 있는가?
            if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
                // 4. 목적지인가?
                if (board[tx][ty] == 'H')
                    continue;

                // 방문했던 곳 재방문 -> 사이클 생성(동전을 무한번 움직일 수 있다)
                if (visited[tx][ty]) {
                    cycleFlag = true;
                    return;
                }

                // 같은 좌표에 더 적은 move로 방문하면 가지치기로 방문하지 않는다
                if (dp[tx][ty] > move)
                    continue;

                // 5. 간다.
                dfs(tx, ty, move + 1);
            }
        }

        // 6. 체크아웃
        visited[x][y] = false;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1103/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        cycleFlag = false;
        maxMove = 0;

        dfs(0, 0, 1);

        if (cycleFlag)
            System.out.println("-1");
        else
            System.out.println(maxMove);
    }
}

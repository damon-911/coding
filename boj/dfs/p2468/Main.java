package boj.dfs.p2468;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N;
    static int maxHeight = 0;
    static int maxArea = 0;
    static int[][] map;
    static boolean[][] visited;

    static int dfs(int curX, int curY, int height) {
        visited[curX][curY] = true;

        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty])
                continue;

            if (map[tx][ty] > height) {
                dfs(tx, ty, height);
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p2468/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > maxHeight) {
                    maxHeight = map[i][j];
                }
            }
        }

        for (int height = 0; height <= maxHeight; height++) {
            visited = new boolean[N][N];
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > height) {
                        cnt += dfs(i, j, height);
                    }
                }
            }

            maxArea = Math.max(maxArea, cnt);
        }

        System.out.println(maxArea);
    }
}

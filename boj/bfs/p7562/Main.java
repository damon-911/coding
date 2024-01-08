package boj.bfs.p7562;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -2, -2, -1, -1, 1, 1, 2, 2 };
    static final int[] MY = { -1, 1, -2, 2, -2, 2, -1, 1 };

    static int bfs(int N, int curX, int curY, int goalX, int goalY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { curX, curY, 0 });

        boolean[][] visited = new boolean[N][N];
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == goalX && cur[1] == goalY) {
                return cur[2];
            }

            for (int i = 0; i < 8; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty]) {
                    continue;
                }

                visited[tx][ty] = true;
                queue.add(new int[] { tx, ty, cur[2] + 1 });
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p7562/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int curX = Integer.parseInt(st.nextToken());
            int curY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int goalX = Integer.parseInt(st.nextToken());
            int goalY = Integer.parseInt(st.nextToken());

            System.out.println(bfs(N, curX, curY, goalX, goalY));
        }
    }
}

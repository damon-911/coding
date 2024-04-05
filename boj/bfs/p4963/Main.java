package boj.bfs.p4963;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0, 1, 1, -1, -1 };
    static final int[] MY = { 0, 0, -1, 1, 1, -1, 1, -1 };

    static int w, h, result;
    static int[][] map;
    static boolean[][] visited;

    static void bfs(int curX, int curY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { curX, curY });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = cur[0] + MX[i];
                int nextY = cur[1] + MY[i];

                if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w || visited[nextX][nextY]) {
                    continue;
                }

                if (map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[] { nextX, nextY });
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p4963/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            result = 0;
            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        visited[i][j] = true;
                        bfs(i, j);
                        result++;
                    }
                }
            }

            System.out.println(result);
        }
    }
}

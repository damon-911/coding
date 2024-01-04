package boj.combinatorics.p1941;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final char SOM = 'S';

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static char[][] desk;
    static boolean[][] visited;
    static int result = 0;

    static boolean check() {
        boolean[][] copyVisited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            copyVisited[i] = visited[i].clone();
        }

        int x = 0;
        int y = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (copyVisited[i][j]) {
                    x = i;
                    y = j;
                }
            }
        }

        // 연속된 7자리인지, 이다솜파가 과반수 이상인지 확인
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { x, y });

        int total = 0;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || tx >= 5 || ty < 0 || ty >= 5) {
                    continue;
                }

                if (copyVisited[tx][ty]) {
                    if (desk[tx][ty] == SOM)
                        cnt++;
                    total++;
                    queue.add(new int[] { tx, ty });
                    copyVisited[tx][ty] = false;
                }
            }
        }

        if (total == 7 && cnt >= 4) {
            return true;
        }
        return false;
    }

    static void dfs(int cur, int depth) {
        if (depth == 7) {
            if (check())
                result++;
            return;
        }

        for (int i = cur; i < 25; i++) {
            visited[i / 5][i % 5] = true;
            dfs(i + 1, depth + 1);
            visited[i / 5][i % 5] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p1941/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        desk = new char[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 5; j++) {
                desk[i][j] = temp.charAt(j);
            }
        }

        dfs(0, 0);

        System.out.println(result);
    }
}
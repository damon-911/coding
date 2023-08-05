package boj.bfs.p16236;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N, time, size, eatCnt;
    static int[][] map;
    static Fish cur;

    static class Fish {
        int x;
        int y;
        int dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static void bfs() {
        while (true) {
            PriorityQueue<Fish> pq = new PriorityQueue<>((f1, f2) -> f1.dist != f2.dist ? f1.dist - f2.dist : (f1.x != f2.x ? f1.x - f2.x : f1.y - f2.y));
            boolean[][] visited = new boolean[N][N];

            pq.add(new Fish(cur.x, cur.y, 0));
            visited[cur.x][cur.y] = true;

            boolean check = false; // 상어가 먹이를 먹었는지 체크할 변수

            while (!pq.isEmpty()) {
                cur = pq.poll();

                // 상어의 사이즈보다 작은 물고기라면
                if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size) {
                    map[cur.x][cur.y] = 0;
                    eatCnt++;
                    time += cur.dist;
                    check = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int tx = cur.x + MX[i];
                    int ty = cur.y + MY[i];

                    if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty] || map[tx][ty] > size)
                        continue;

                    pq.add(new Fish(tx, ty, cur.dist + 1));
                    visited[tx][ty] = true;
                }
            }

            // 먹은 물고기가 없다면
            if (!check)
                break;

            // 사이즈와 먹은 물고기 수가 동일하다면
            if (size == eatCnt) {
                size++;
                eatCnt = 0;
            }
        }

        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p16236/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        time = 0;
        size = 2;
        eatCnt = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    cur = new Fish(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        bfs();
    }
}

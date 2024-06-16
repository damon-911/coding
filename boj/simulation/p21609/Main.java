package boj.simulation.p21609;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static final int EMPTY = -2;
    static final int BLACK = -1;
    static final int RAINBOW = 0;

    static int N, M, result;
    static int[][] map, temp;
    static boolean[][] visited;

    static class Block {
        // 기준 블록의 x 좌표
        int x;
        // 기준 블록의 y 좌표
        int y;
        // 블록 색상
        int color;
        // 블록그룹의 총 블록 수
        int cnt;
        // 블록그룹에 속한 무지개 블록 수
        int rainbowCnt;

        public Block(int x, int y, int color, int cnt, int rainbowCnt) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.cnt = cnt;
            this.rainbowCnt = rainbowCnt;
        }

        public boolean compareBlock(Block block) {
            // 블록 수로 비교
            if (this.cnt != block.cnt)
                return this.cnt < block.cnt;

            // 무지개 블록 수로 비교
            if (this.rainbowCnt != block.rainbowCnt)
                return this.rainbowCnt < block.rainbowCnt;

            // 행으로 비교
            if (this.x != block.x)
                return this.x < block.x;

            // 열로 비교
            return this.y < block.y;
        }
    }

    static void rotate() {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[j][N - i - 1];
            }
        }
        map = copyMap.clone();
    }

    static void moveBlock(int x, int y) {
        int curX = x;

        while (true) {
            curX++;
            if (curX >= N || map[curX][y] != EMPTY) {
                break;
            }
        }
        curX--;

        if (curX == x) {
            return;
        }

        map[curX][y] = map[x][y];
        map[x][y] = EMPTY;
    }

    static void applyGravity() {
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (map[j][i] == EMPTY || map[j][i] == BLACK) {
                    continue;
                }
                moveBlock(j, i);
            }
        }
    }

    static void removeBlock(Block block) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { block.x, block.y });

        visited = new boolean[N][N];
        visited[block.x][block.y] = true;

        map[block.x][block.y] = EMPTY;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N) {
                    continue;
                }

                if (visited[tx][ty] || map[tx][ty] == EMPTY || map[tx][ty] == BLACK) {
                    continue;
                }

                if (map[tx][ty] != block.color) {
                    if (map[tx][ty] == RAINBOW) {
                        map[tx][ty] = EMPTY;
                        visited[tx][ty] = true;
                        queue.offer(new int[] { tx, ty });
                    }
                    continue;
                }

                map[tx][ty] = EMPTY;
                visited[tx][ty] = true;
                queue.offer(new int[] { tx, ty });
            }
        }
    }

    static Block bfs(int x, int y, int color) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });

        visited[x][y] = true;

        int cnt = 1;
        int rainbowCnt = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N) {
                    continue;
                }

                if (visited[tx][ty] || map[tx][ty] == EMPTY || map[tx][ty] == BLACK) {
                    continue;
                }

                if (map[tx][ty] != color) {
                    if (map[tx][ty] == RAINBOW) {
                        cnt++;
                        rainbowCnt++;
                        visited[tx][ty] = true;
                        queue.offer(new int[] { tx, ty });
                    }
                    continue;
                }

                cnt++;
                visited[tx][ty] = true;
                queue.offer(new int[] { tx, ty });
            }
        }

        if (cnt < 2) {
            return null;
        } else {
            return new Block(x, y, color, cnt, rainbowCnt);
        }
    }

    static void initRainbowBlock() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == RAINBOW) {
                    visited[i][j] = false;
                }
            }
        }
    }

    static Block findMaxBlockGroup() {
        visited = new boolean[N][N];
        Block maxBlock = new Block(0, 0, 0, -1, -1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == EMPTY || map[i][j] == BLACK || map[i][j] == RAINBOW) {
                    continue;
                }

                // 무지개 블록만 visited 정보 초기화
                initRainbowBlock();

                Block cur = bfs(i, j, map[i][j]);

                if (cur == null) {
                    continue;
                }

                if (maxBlock.compareBlock(cur)) {
                    maxBlock = cur;
                }
            }
        }

        if (maxBlock.color == 0) {
            return null;
        }
        return maxBlock;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p21609/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        while (true) {
            Block block = findMaxBlockGroup();

            if (block == null) {
                break;
            }

            result += block.cnt * block.cnt;

            removeBlock(block);

            applyGravity();

            rotate();

            applyGravity();
        }

        System.out.println(result);
    }
}
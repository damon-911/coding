package boj.graph.p17472;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 1, 0, 0 };
    static final int[] MY = { 0, 0, -1, 1 };

    static int N, M, islandCnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] parents;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int start;
        int dest;
        int cost;

        public Edge(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }

    static void union(int element1, int element2) {
        int root1 = find(element1);
        int root2 = find(element2);

        if (root1 < root2) {
            parents[root2] = root1;
        } else {
            parents[root1] = root2;
        }
    }

    static int find(int element) {
        if (parents[element] == element) {
            return element;
        }
        return find(parents[element]);
    }

    static int kruskal() {
        int minCost = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.start) != find(edge.dest)) {
                minCost += edge.cost;
                union(edge.start, edge.dest);
            }
        }

        for (int i = 2; i < islandCnt; i++) {
            if (parents[1] != find(parents[i])) {
                return 0;
            }
        }

        return minCost;
    }

    static void makeBridge(int curX, int curY, int idx) {
        boolean[][] check = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            queue.add(new int[] { curX, curY, 0 });
            check[curX][curY] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= M || check[tx][ty]) {
                    continue;
                }

                if (map[tx][ty] != idx) {
                    if (map[tx][ty] != 0) {
                        if (cur[2] > 1) {
                            pq.add(new Edge(idx - 1, map[tx][ty] - 1, cur[2]));
                            break;
                        }
                    } else {
                        check[tx][ty] = true;
                        queue.add(new int[] { tx, ty, cur[2] + 1 });
                    }
                }
            }
            queue.clear();
        }
    }

    static void findIsland(int curX, int curY, int idx) {
        map[curX][curY] = idx;
        visited[curX][curY] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { curX, curY });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= M || visited[tx][ty]) {
                    continue;
                }

                if (map[tx][ty] == 1) {
                    map[tx][ty] = idx;
                    visited[tx][ty] = true;
                    queue.add(new int[] { tx, ty });
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p17472/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandCnt = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    findIsland(i, j, islandCnt);
                    islandCnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    makeBridge(i, j, map[i][j]);
                }
            }
        }

        islandCnt--;
        parents = new int[islandCnt];
        for (int i = 1; i < islandCnt; i++) {
            parents[i] = i;
        }

        int result = kruskal();
        System.out.println(result == 0 ? -1 : result);
    }
}
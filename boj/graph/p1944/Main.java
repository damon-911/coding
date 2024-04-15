package boj.graph.p1944;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char WALL = '1';
    static final char START = 'S';
    static final char KEY = 'K';

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M;
    static char[][] map;
    static int[] parent;
    static List<Node> nodeList = new ArrayList<>();
    static PriorityQueue<Mst> pq = new PriorityQueue<>();

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static class Mst implements Comparable<Mst> {
        int start, dest, cost;

        public Mst(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Mst mst) {
            return this.cost - mst.cost;
        }
    }

    public static void union(int p1, int p2) {
        parent[p1] = p2;
    }

    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    public static int kruskal() {
        parent = new int[M + 1];
        for (int i = 0; i < M + 1; i++) {
            parent[i] = i;
        }

        int cost = 0;
        int edgeCnt = 0;
        while (!pq.isEmpty()) {
            Mst cur = pq.poll();

            int parent1 = find(cur.start);
            int parent2 = find(cur.dest);

            if (parent1 != parent2) {
                union(parent1, parent2);
                cost += cur.cost;
                edgeCnt++;
            }
        }

        // 모두 연결되지 않았다면 모든 열쇠를 찾는 것이 불가능한 경우임
        if (edgeCnt != M) {
            return -1;
        }

        return cost;
    }

    static void bfs(int idx) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(nodeList.get(idx));

        boolean[][] visited = new boolean[N][N];
        visited[nodeList.get(idx).x][nodeList.get(idx).y] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == WALL || visited[tx][ty]) {
                    continue;
                }

                visited[tx][ty] = true;

                if (map[tx][ty] == START || map[tx][ty] == KEY) {
                    for (int j = 0; j < M + 1; j++) {
                        if (nodeList.get(j).x == tx && nodeList.get(j).y == ty) {
                            pq.offer(new Mst(idx, j, cur.cnt + 1));
                        }
                    }
                }

                queue.offer(new Node(tx, ty, cur.cnt + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1944/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == START || map[i][j] == KEY) {
                    nodeList.add(new Node(i, j, 0));
                }
            }
        }

        // nodeList의 크기 : 출발점 + 키의 수 = 1 + M
        for (int i = 0; i < M + 1; i++) {
            bfs(i);
        }

        System.out.println(kruskal());
    }
}

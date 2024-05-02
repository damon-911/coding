package boj.graph.p1238;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1_000_000_000;

    static int N, M, X;
    static List<List<Edge>> normalGraph, reverseGraph;

    static class Edge implements Comparable<Edge> {
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }

    static int[] dijkstra(List<List<Edge>> graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));

        boolean[] check = new boolean[N + 1];

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.dest;

            if (check[cur]) {
                continue;
            }

            check[cur] = true;
            for (Edge next : graph.get(cur)) {
                if (!check[next.dest] && dist[next.dest] > dist[cur] + next.cost) {
                    dist[next.dest] = dist[cur] + next.cost;
                    pq.add(new Edge(next.dest, dist[next.dest]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1238/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        normalGraph = new ArrayList<>(); // X에서 모든 정점 사이의 최단거리
        reverseGraph = new ArrayList<>(); // 모든 정점에서 X 사이의 최단거리
        for (int i = 0; i <= N; i++) {
            normalGraph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            normalGraph.get(A).add(new Edge(B, C));
            reverseGraph.get(B).add(new Edge(A, C));
        }

        int[] dist1 = dijkstra(normalGraph);
        int[] dist2 = dijkstra(reverseGraph);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dist1[i] + dist2[i]);
        }

        System.out.println(result);
    }
}

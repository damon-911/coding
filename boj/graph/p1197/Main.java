package boj.graph.p1197;

import java.io.*;
import java.util.*;

public class Main {

    static int V, E;
    static List<List<Edge>> graph;
    static PriorityQueue<Edge> pq;

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

    static void prim() {
        boolean[] visited = new boolean[V + 1];

        pq.offer(new Edge(1, 0));

        int minCost = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int dest = edge.dest;
            int cost = edge.cost;

            // 방문 했다면 건너뜀
            if (visited[dest])
                continue;

            visited[dest] = true;
            minCost += cost;

            // 해당 정점에 연결되어 있는 모든 간선 탐색
            for (Edge e : graph.get(dest)) {
                if (!visited[e.dest]) {
                    pq.add(e);
                }
            }
        }

        System.out.println(minCost);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1197/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
        }

        pq = new PriorityQueue<>();

        prim();
    }
}

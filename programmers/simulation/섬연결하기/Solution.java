package programmers.simulation.섬연결하기;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static int minCost;
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

    private static void prim(int n) {
        boolean[] visited = new boolean[n];

        pq.offer(new Edge(0, 0));

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
    }

    public static int solution(int n, int[][] costs) {
        minCost = 0;

        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] cost : costs) {
            graph.get(cost[0]).add(new Edge(cost[1], cost[2]));
            graph.get(cost[1]).add(new Edge(cost[0], cost[2]));
        }

        pq = new PriorityQueue<>();

        prim(n);

        return minCost;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {
                { 0, 1, 1 },
                { 0, 2, 2 },
                { 1, 2, 5 },
                { 1, 3, 1 },
                { 2, 3, 8 }
        };

        System.out.println(solution(n, costs));
    }
}

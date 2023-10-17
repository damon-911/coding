package programmers.graph.합승택시요금;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    static int[] dijkstra(int start, int[] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curDest = cur.dest;
            int curCost = cur.cost;

            if (curCost > costs[curDest])
                continue;

            List<Node> nodes = graph.get(curDest);
            for (Node node : nodes) {
                int nextCost = costs[curDest] + node.cost;

                if (nextCost < costs[node.dest]) {
                    costs[node.dest] = nextCost;
                    pq.offer(new Node(node.dest, nextCost));
                }
            }
        }
        return costs;
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            graph.get(fare[0]).add(new Node(fare[1], fare[2]));
            graph.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        int[] startS = new int[n + 1];
        int[] startA = new int[n + 1];
        int[] startB = new int[n + 1];

        Arrays.fill(startS, Integer.MAX_VALUE);
        Arrays.fill(startA, Integer.MAX_VALUE);
        Arrays.fill(startB, Integer.MAX_VALUE);

        startS = dijkstra(s, startS);
        startA = dijkstra(a, startA);
        startB = dijkstra(b, startB);

        for (int i = 1; i <= n; i++)
            answer = Math.min(answer, startS[i] + startA[i] + startB[i]);

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {
                { 4, 1, 10 },
                { 3, 5, 24 },
                { 5, 6, 2 },
                { 3, 1, 41 },
                { 5, 1, 24 },
                { 4, 6, 50 },
                { 2, 4, 66 },
                { 2, 3, 22 },
                { 1, 6, 25 }
        };

        System.out.println(solution(n, s, a, b, fares));
    }
}

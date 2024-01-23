package boj.graph.p18223;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, P;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next : graph.get(cur.dest)) {
                if (dist[next.dest] > cur.cost + next.cost) {
                    dist[next.dest] = cur.cost + next.cost;
                    pq.add(new Node(next.dest, cur.cost + next.cost));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p18223/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int[] dist1 = dijkstra(1);
        int[] dist2 = dijkstra(P);

        if (dist1[V] >= dist1[P] + dist2[V])
            System.out.println("SAVE HIM");
        else
            System.out.println("GOOD BYE");
    }
}
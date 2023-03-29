package boj.graph.p1922;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] computers;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int current;
        int dest;
        int cost;

        public Edge(int current, int dest, int cost) {
            this.current = current;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

    static int find(int element) {
        if (computers[element] == element)
            return element;
        else {
            return computers[element] = find(computers[element]);
        }
    }

    static void union(int element1, int element2) {
        // element1과 element2의 대표 노드 확인
        int root1 = find(element1);
        int root2 = find(element2);

        // element1과 element2의 대표 노드가 다를 경우
        if (root1 != root2)
            computers[root1] = root2;
    }

    static void kruskal() {
        int minCost = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.current) != find(edge.dest)) {
                union(edge.current, edge.dest);
                minCost += edge.cost;
            }
        }

        System.out.println(minCost);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1922/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        computers = new int[N + 1];
        for (int num = 0; num <= N; num++) {
            computers[num] = num;
        }

        pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, c));
        }

        kruskal();
    }
}

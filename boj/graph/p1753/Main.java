package boj.graph.p1753;

import java.io.*;
import java.util.*;

public class Main {

    static int V, E, K;
    static ArrayList<ArrayList<Edge>> list;
    static PriorityQueue<Edge> pq;
    static int[] minLength;

    static class Edge {
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }

    static void findMinLength() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int D = edge.dest;
            int C = edge.cost;

            // 현재 우선순위 큐에서 뺀 것과 기존 계산된 최단거리랑 비교했을 때
            // 우선순위 큐에서 뺀 것이 더 큰 거리라면 continue;

            int len = list.get(D).size();

            // 인접 리스트로 비교
            for (int i = 0; i < len; i++) {
                int nextD = list.get(D).get(i).dest;
                int nextC = list.get(D).get(i).cost;

                // dest에서 다음 목적지로 가는 거리가 현재 계산된 최단 거리보다 작다면
                if (minLength[nextD] > minLength[D] + nextC) {
                    // 최단거리 테이블을 갱신
                    minLength[nextD] = minLength[D] + nextC;
                    // 우선순위 큐에 넣는다
                    pq.offer(new Edge(nextD, minLength[nextD]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (minLength[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(minLength[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        minLength = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            if (i == K)
                minLength[i] = 0;
            else
                minLength[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Edge(v, w));
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        pq.offer(new Edge(K, 0));

        findMinLength();
    }
}

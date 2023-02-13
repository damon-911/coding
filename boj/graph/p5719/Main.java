package boj.graph.p5719;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, S, D;
    static ArrayList<ArrayList<Road>> graph;
    static PriorityQueue<Road> pq;
    static int[] minLength;
    static Queue<Integer> pathQueue;

    static class Road {
        int dest;
        int length;

        public Road(int dest, int length) {
            this.dest = dest;
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    static void findMinLength() {
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            int RD = road.dest;
            int RL = road.length;

            // 현재 우선순위 큐에서 뺀 것과 기존 계산된 최단거리랑 비교했을 때
            // 우선순위 큐에서 뺀 것이 더 큰 거리라면 continue;

            int len = graph.get(RD).size();

            // 인접 리스트로 비교
            for (int i = 0; i < len; i++) {
                int nextRD = graph.get(RD).get(i).dest;
                int nextRL = graph.get(RD).get(i).length;

                // dest에서 다음 목적지로 가는 거리가 현재 계산된 최단 거리보다 작다면
                if (minLength[nextRD] > minLength[RD] + nextRL) {
                    // 최단거리 테이블을 갱신
                    minLength[nextRD] = minLength[RD] + nextRL;
                    // 우선순위 큐에 넣는다
                    pq.offer(new Road(nextRD, minLength[nextRD]));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (minLength[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(minLength[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;

            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph.get(U).add(new Road(V, P));
            }

            minLength = new int[N];
            for (int i = 0; i < N; i++) {
                if (i == S)
                    minLength[i] = 0;
                else
                    minLength[i] = Integer.MAX_VALUE;
            }

            pq = new PriorityQueue<>(Comparator.comparingInt(Road::getLength));
            pq.offer(new Road(S, 0));

            findMinLength();
        }
    }
}

package boj.graph.p1916;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<List<Bus>> cities;
    static PriorityQueue<Bus> pq;
    static int[] minCost;

    static class Bus {
        int dest;
        int cost;

        public Bus(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }

    static void findMinCost() {
        boolean[] check = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Bus bus = pq.poll();
            int curDest = bus.dest;

            if (check[curDest])
                continue;

            for (int i = 0; i < cities.get(curDest).size(); i++) {
                int nextDest = cities.get(curDest).get(i).dest;
                int nextCost = cities.get(curDest).get(i).cost;

                // dest에서 다음 목적지로 가는 거리가 현재 계산된 최단 거리보다 작다면
                if (minCost[nextDest] > minCost[curDest] + nextCost) {
                    minCost[nextDest] = minCost[curDest] + nextCost;
                    pq.offer(new Bus(nextDest, minCost[nextDest]));
                }
            }

            check[curDest] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1916/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cities = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            cities.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            cities.get(start).add(new Bus(dest, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int destCity = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>(Comparator.comparingInt(Bus::getCost));
        pq.offer(new Bus(startCity, 0));

        minCost = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i == startCity)
                minCost[i] = 0;
            else
                minCost[i] = Integer.MAX_VALUE;
        }

        findMinCost();

        System.out.println(minCost[destCity]);
    }
}

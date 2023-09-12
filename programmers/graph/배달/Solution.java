package programmers.graph.배달;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static List<List<Road>> graph;
    static int[] minLength;
    static PriorityQueue<Road> pq;
    static int answer;

    static class Road {
        int dest;
        int cost;

        public Road(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }

    static void findMinLength(int N, int K) {
        while (!pq.isEmpty()) {
            Road road = pq.poll();

            for (int i = 0; i < graph.get(road.dest).size(); i++) {
                int nextDest = graph.get(road.dest).get(i).dest;
                int nextCost = graph.get(road.dest).get(i).cost;

                if (minLength[nextDest] > minLength[road.dest] + nextCost) {
                    minLength[nextDest] = minLength[road.dest] + nextCost;
                    pq.offer(new Road(nextDest, minLength[nextDest]));
                }
            }
        }

        for (int i = 1; i < minLength.length; i++) {
            if (minLength[i] <= K)
                answer++;
        }
    }

    public static int solution(int N, int[][] road, int K) {
        answer = 0;

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] way : road) {
            graph.get(way[0]).add(new Road(way[1], way[2]));
            graph.get(way[1]).add(new Road(way[0], way[2]));
        }

        minLength = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            minLength[i] = Integer.MAX_VALUE;
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(Road::getCost));
        pq.offer(new Road(1, 0));

        findMinLength(N, K);

        return answer;
    }

    public static void main(String[] args) {
        int N = 5;
        int[][] road = {
                { 1, 2, 1 },
                { 2, 3, 3 },
                { 5, 2, 2 },
                { 1, 4, 2 },
                { 5, 3, 1 },
                { 5, 4, 2 }
        };
        int K = 3;

        System.out.println(solution(N, road, K));
    }
}

package programmers.data_structure.섬연결하기;

import java.util.PriorityQueue;

public class Solution {
    static PriorityQueue<Bridge> pq;
    static int[] island;

    static class Bridge implements Comparable<Bridge> {
        int start;
        int dest;
        int cost;

        public Bridge(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge b) {
            return this.cost - b.cost;
        }
    }

    private static void union(int a, int b) {
        // element1과 element2의 대표 노드 확인
        int root1 = find(a);
        int root2 = find(b);

        // element1과 element2의 대표 노드가 다를 경우
        if (root1 != root2)
            island[root1] = root2;
    }

    private static int find(int a) {
        // 찾는 대상과 인덱스 번호가 같다면 그 인덱스가 해당 집합의 부모이다
        if (island[a] == a)
            return a;
        else {
            // 해당 인덱스가 가리키는 값(부모 노드)을 따라 최종 부모 노드까지 탐색
            return island[a] = find(island[a]);
        }
    }

    // 크루스칼 알고리즘
    private static int kruskal() {
        int minCost = 0;

        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();

            if (find(bridge.start) != find(bridge.dest)) {
                union(bridge.start, bridge.dest);
                minCost += bridge.cost;
            }
        }

        return minCost;
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;

        island = new int[n];
        for (int i = 0; i < n; i++) {
            island[i] = i;
        }

        pq = new PriorityQueue<>();
        for (int[] cost : costs) {
            pq.add(new Bridge(cost[0], cost[1], cost[2]));
        }

        answer = kruskal();

        return answer;
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
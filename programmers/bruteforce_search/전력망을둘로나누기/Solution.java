package programmers.bruteforce_search.전력망을둘로나누기;

import java.util.ArrayList;

public class Solution {

    static ArrayList<Integer>[] graph;
    static int min;

    private static int dfs(int v, boolean[] visited) {
        visited[v] = true;
        int cnt = 1;

        for (int next : graph[v]) {
            if (!visited[next]) {
                cnt += dfs(next, visited);
            }
        }

        return cnt;
    }

    public static int solution(int n, int[][] wires) {
        graph = new ArrayList[n + 1];
        min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            boolean[] visited = new boolean[n + 1];

            // 해당 간선을 그래프에서 제거
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            int cnt = dfs(1, visited);

            int diff = Math.abs(cnt - (n - cnt));
            min = Math.min(min, diff);

            // 그래프에 다시 간선 추가
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return min;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {
                { 1, 3 },
                { 2, 3 },
                { 3, 4 },
                { 4, 5 },
                { 4, 6 },
                { 4, 7 },
                { 7, 8 },
                { 7, 9 }
        };

        System.out.println(solution(n, wires));
    }
}
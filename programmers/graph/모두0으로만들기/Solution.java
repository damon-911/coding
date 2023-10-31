package programmers.graph.모두0으로만들기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    static List<List<Integer>> graph;
    static long[] aLong;
    static int[] visited;
    static int root = 0; // root 노드를 항상 0으로 설정
    static long answer = 0;

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < aLong.length; i++) {
            if (graph.get(i).size() == 1) { // leaf 노드
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            answer += Math.abs(aLong[cur]);
            visited[cur]--;

            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);

                if (visited[next] == 0) {
                    continue;
                }

                visited[next]--;
                aLong[next] += aLong[cur];

                if (visited[next] == 1) {
                    if (next == root) {
                        continue;
                    }
                    queue.add(next);
                }
            }
        }
    }

    public static long solution(int[] a, int[][] edges) {
        aLong = new long[a.length];

        for (int i = 0; i < a.length; i++) {
            aLong[i] = a[i];
            answer += aLong[i];
        }

        if (answer != 0) {
            return -1;
        }

        visited = new int[a.length];

        graph = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            visited[edge[0]]++;
            visited[edge[1]]++;
        }

        bfs();

        return answer;
    }

    public static void main(String[] args) {
        int[] a = { -5, 0, 2, 1, 2 };
        int[][] edges = {
                { 0, 1 },
                { 3, 4 },
                { 2, 3 },
                { 0, 3 }
        };

        System.out.println(solution(a, edges));
    }
}

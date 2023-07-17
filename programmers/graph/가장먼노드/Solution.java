package programmers.graph.가장먼노드;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    static boolean[] visited;
    static List<List<Integer>> graph;

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 1, 0 });
        visited[1] = true;

        int count = 0;
        int maxDepth = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int v = arr[0];
            int depth = arr[1];

            if (maxDepth == depth)
                count++;
            else if (maxDepth < depth) {
                maxDepth = depth;
                count = 1;
            }

            for (int i = 0; i < graph.get(v).size(); i++) {
                int dest = graph.get(v).get(i);

                if (!visited[dest]) {
                    queue.add(new int[] { dest, depth + 1 });
                    visited[dest] = true;
                }
            }
        }

        return count;
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;

        visited = new boolean[n + 1];

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        answer = bfs();

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {
                { 3, 6 },
                { 4, 3 },
                { 3, 2 },
                { 1, 3 },
                { 1, 2 },
                { 2, 4 },
                { 5, 2 }
        };

        System.out.println(solution(n, vertex));
    }
}

package programmers.graph.부대복귀;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    static List<List<Integer>> graph;
    static int[] dis;

    static void dijkstra(int destination) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dis[destination] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (dis[next] > dis[cur] + 1) {
                    dis[next] = dis[cur] + 1;
                    queue.add(next);
                }
            }
        }
    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dijkstra(destination);

        for (int i = 0; i < sources.length; i++) {
            answer[i] = (dis[sources[i]] < Integer.MAX_VALUE) ? dis[sources[i]] : -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] roads = { { 1, 2 }, { 2, 3 } };
        int[] sources = { 2, 3 };
        int destination = 1;

        System.out.println(Arrays.toString(solution(n, roads, sources, destination)));
    }
}

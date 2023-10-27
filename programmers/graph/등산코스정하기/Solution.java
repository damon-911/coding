package programmers.graph.등산코스정하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    static int[] answer;
    static List<List<Path>> graph;

    static class Path {
        int dest;
        int cost;

        public Path(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (num == gate) {
                return true;
            }
        }
        return false;
    }

    static boolean isSummit(int num, int[] summits) {
        for (int summit : summits) {
            if (num == summit) {
                return true;
            }
        }
        return false;
    }

    static void dijkstra(int n, int[] gates, int[] summits) {
        Queue<Path> queue = new LinkedList<>();

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // 출입구 전부를 큐에 넣음
        for (int gate : gates) {
            queue.add(new Path(gate, 0));
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Path cur = queue.poll();

            // 현재의 가중치가 저장된 가중치보다 커서 최소 갱신이 되지 않을 때 스킵
            if (cur.cost > intensity[cur.dest]) {
                continue;
            }

            for (int i = 0; i < graph.get(cur.dest).size(); i++) {
                Path next = graph.get(cur.dest).get(i);

                // 최소 intensity 갱신
                int dis = Math.max(intensity[cur.dest], next.cost);
                if (intensity[next.dest] > dis) {
                    intensity[next.dest] = dis;
                    queue.add(new Path(next.dest, dis));
                }
            }
        }

        int mount = Integer.MAX_VALUE; // 산봉우리 번호
        int min = Integer.MAX_VALUE; // 최솟값

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < min) {
                mount = summit;
                min = intensity[summit];
            }
        }

        answer[0] = mount;
        answer[1] = min;
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        answer = new int[2];

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            if (isGate(path[0], gates) || isSummit(path[1], summits)) {
                graph.get(path[0]).add(new Path(path[1], path[2]));
            } else if (isGate(path[1], gates) || isSummit(path[0], summits)) {
                graph.get(path[1]).add(new Path(path[0], path[2]));
            } else {
                graph.get(path[0]).add(new Path(path[1], path[2]));
                graph.get(path[1]).add(new Path(path[0], path[2]));
            }
        }

        dijkstra(n, gates, summits);

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {
                { 1, 2, 3 },
                { 2, 3, 5 },
                { 2, 4, 2 },
                { 2, 5, 4 },
                { 3, 4, 4 },
                { 4, 5, 3 },
                { 4, 6, 1 },
                { 5, 6, 1 }
        };
        int[] gates = { 1, 3 };
        int[] summits = { 5 };

        System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
    }
}

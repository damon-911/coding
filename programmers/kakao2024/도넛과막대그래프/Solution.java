package programmers.kakao2024.도넛과막대그래프;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static int maxVertex = 0;
    static int startVertex;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] inCount;

    static void initGraph(int[][] edges) {
        for (int[] edge : edges) {
            maxVertex = Math.max(maxVertex, Math.max(edge[0], edge[1]));
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= maxVertex; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[maxVertex + 1];
        inCount = new int[maxVertex + 1];

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inCount[edge[1]]++;
        }
    }

    static int findStartVertex() {
        int result = 0;
        for (int i = 1; i <= maxVertex; i++) {
            // 들어오는 간선은 없고 나가는 간선은 2개 이상인 정점 찾기
            if (inCount[i] == 0 && graph.get(i).size() >= 2) {
                result = i;
                break;
            }
        }
        visited[result] = true;
        return result;
    }

    static void removeStartVertex() {
        for (int vertex : graph.get(startVertex)) {
            inCount[vertex]--;
        }
        graph.set(startVertex, new ArrayList<>());
    }

    static int findBarGraph() {
        int count = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (i == startVertex) {
                continue;
            }
            // 다른 정점으로 나가는 간선이 없는 정점 찾기
            if (graph.get(i).isEmpty()) {
                count++;
                visited[i] = true;
            }
        }
        return count;
    }

    static int findEightShapeGraph() {
        int count = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                // 들어오는 간선이 2개이면서 나가는 간선은 2개인 정점 찾기
                if (inCount[i] == 2 && graph.get(i).size() == 2) {
                    System.out.println(i);
                    count++;
                }
            }
        }
        return count;
    }

    public static int[] solution(int[][] edges) {
        int[] answer = new int[4];

        initGraph(edges);

        startVertex = findStartVertex();
        int graphNum = graph.get(startVertex).size();

        removeStartVertex();

        answer[0] = startVertex;
        answer[2] = findBarGraph();
        answer[3] = findEightShapeGraph();
        answer[1] = graphNum - (answer[2] + answer[3]);

        return answer;
    }

    public static void main(String[] args) {
        int[][] edges = { { 2, 3 }, { 4, 3 }, { 1, 1 }, { 2, 1 } };

        System.out.println(Arrays.toString(solution(edges)));
    }
}
package programmers.graph.등대;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static List<List<Integer>> graph;
    static int answer;

    static int dfs(int cur, int before) {
        // 리프 노드일 경우
        if (graph.get(cur).size() == 1 && graph.get(cur).get(0) == before)
            return 1;

        // 리프 노드가 아닐 경우
        int result = 0;
        for (int i = 0; i < graph.get(cur).size(); i++) {
            int next = graph.get(cur).get(i);
            if (next == before)
                continue;
            result += dfs(next, cur);
        }

        // 해당 노드가 불을 킬 필요 없음
        if (result == 0)
            return 1;

        // 해당 노드가 불을 켜야함
        answer++;
        return 0;
    }

    public static int solution(int n, int[][] lighthouse) {
        answer = 0;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : lighthouse) {
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }

        dfs(1, 0);

        return answer;
    }

    public static void main(String[] args) {
        int n = 8;
        int[][] lighthouse = {
                { 1, 2 },
                { 1, 3 },
                { 1, 4 },
                { 1, 5 },
                { 5, 6 },
                { 5, 7 },
                { 5, 8 }
        };

        System.out.println(solution(n, lighthouse));
    }
}

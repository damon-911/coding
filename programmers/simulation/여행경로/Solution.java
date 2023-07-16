package programmers.simulation.여행경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    static boolean[] visited;
    static List<String> answers;

    private static void dfs(int depth, String cur, String path, String[][] tickets) {
        if (depth == tickets.length) {
            answers.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && cur.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }

    public static String[] solution(String[][] tickets) {
        answers = new ArrayList<>();

        visited = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(answers);

        return answers.get(0).split(" ");
    }

    public static void main(String[] args) {
        String[][] tickets = {
                { "ICN", "JFK" },
                { "HND", "IAD" },
                { "JFK", "HND" }
        };

        System.out.println(Arrays.toString(solution(tickets)));
    }
}
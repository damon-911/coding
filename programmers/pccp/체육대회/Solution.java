package programmers.pccp.체육대회;

public class Solution {

    static int max;
    static boolean[] visited;

    private static void perm(int N, int r, int depth, int[][] ability, int sum) {
        if (r == depth) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm(N, r, depth + 1, ability, sum + ability[i][depth]);
                visited[i] = false;
            }
        }
    }

    public static int solution(int[][] ability) {
        max = 0;

        visited = new boolean[ability.length];

        perm(ability.length, ability[0].length, 0, ability, 0);

        return max;
    }

    public static void main(String[] args) {
        int[][] ability = {
                { 40, 10, 10 },
                { 20, 5, 0 },
                { 30, 30, 30 },
                { 70, 0, 70 },
                { 100, 100, 100 }
        };

        System.out.println(solution(ability));
    }
}
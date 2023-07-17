package programmers.graph.순위;

public class Solution {

    public static int solution(int n, int[][] results) {
        int answer = 0;

        int[][] floyd = new int[n + 1][n + 1];

        for (int[] result : results) {
            floyd[result[0]][result[1]] = 1;
            floyd[result[1]][result[0]] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (floyd[i][k] == 1 && floyd[k][j] == 1) {
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }

                    if (floyd[i][k] == -1 && floyd[k][j] == -1) {
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (floyd[i][j] != 0)
                    cnt++;
            }

            // n명의 선수가 있을 때, n-1번의 승패를 알아야 순위를 확정 지을 수 있다
            if (cnt == n - 1)
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {
                { 4, 3 },
                { 4, 2 },
                { 3, 2 },
                { 1, 2 },
                { 2, 5 }
        };

        System.out.println(solution(n, results));
    }
}

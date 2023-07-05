package programmers.kakao.파괴되지않은건물;

public class Solution {

    static int[][] sum;
    static int N, M;

    // 누적합 계산
    private static void operate() {
        // 상하
        for (int x = 1; x < N; x++) {
            for (int y = 0; y < M; y++) {
                sum[x][y] += sum[x - 1][y];
            }
        }

        // 좌우
        for (int x = 0; x < N; x++) {
            for (int y = 1; y < N; y++) {
                sum[x][y] += sum[x][y - 1];
            }
        }
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        N = board.length;
        M = board[0].length;

        sum = new int[N + 1][M + 1];

        for (int[] s : skill) {
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = s[5] * (s[0] == 1 ? -1 : 1);

            sum[x1][y1] += degree;
            sum[x1][y2 + 1] += (degree * -1);
            sum[x2 + 1][y1] += (degree * -1);
            sum[x2 + 1][y2 + 1] += degree;
        }

        operate();

        // 살아남은 건물 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0)
                    answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 5, 5, 5, 5, 5 },
                { 5, 5, 5, 5, 5 },
                { 5, 5, 5, 5, 5 },
                { 5, 5, 5, 5, 5 }
        };
        int[][] skill = {
                { 1, 0, 0, 3, 4, 4 },
                { 1, 2, 0, 2, 3, 2 },
                { 2, 1, 0, 3, 1, 2 },
                { 1, 0, 1, 3, 3, 1 }
        };

        System.out.println(solution(board, skill));
    }
}
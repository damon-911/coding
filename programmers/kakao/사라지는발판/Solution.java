package programmers.kakao.사라지는발판;

public class Solution {

    static int[] MX = { 1, -1, 0, 0 };
    static int[] MY = { 0, 0, 1, -1 };

    static int N, M;

    private static int dfs(int[][] board, int x_A, int y_A, int x_B, int y_B) {
        if (board[x_A][y_A] == 0)
            return 0;

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x_A + MX[i];
            int nextY = y_A + MY[i];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || board[nextX][nextY] == 0)
                continue;

            board[x_A][y_A] = 0;
            int val = dfs(board, x_B, y_B, nextX, nextY) + 1;
            board[x_A][y_A] = 1;

            if (result % 2 == 0 && val % 2 == 1) {
                result = val;
            } else if (result % 2 == 0 && val % 2 == 0) {
                result = result > val ? result : val;
            } else if (result % 2 == 1 && val % 2 == 1) {
                result = result < val ? result : val;
            }
        }

        return result;
    }

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;

        N = board.length;
        M = board[0].length;

        answer = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        int[] aloc = { 1, 0 };
        int[] bloc = { 1, 2 };

        System.out.println(solution(board, aloc, bloc));
    }
}
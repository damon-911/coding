package programmers.simulation.이웃한칸;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    public static int solution(String[][] board, int h, int w) {
        int answer = 0;

        int n = board.length;

        String target = board[h][w];

        for (int i = 0; i < 4; i++) {
            int tx = h + MX[i];
            int ty = w + MY[i];

            if (tx < 0 || ty < 0 || tx >= n || ty >= n) {
                continue;
            }

            if (board[tx][ty].equals(target)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] board = {
                { "blue", "red", "orange", "red" },
                { "red", "red", "blue", "orange" },
                { "blue", "orange", "red", "red" },
                { "orange", "orange", "red", "blue" }
        };
        int h = 1;
        int w = 1;

        System.out.println(solution(board, h, w));
    }
}
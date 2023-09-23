package programmers.simulation.N_Queen;

public class Solution {

    static int answer, N;
    static int[] board;

    static boolean canLocate(int col) {
        for (int i = 0; i < col; i++) {
            // 같은 행에 존재할 경우
            if (board[col] == board[i]) {
                return false;
            }
            // 대각선상에 놓여있는 경우
            else if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }

        return true;
    }

    static void nQueen(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[depth] = i;
            if (canLocate(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static int solution(int n) {
        answer = 0;

        N = n;

        board = new int[n];

        nQueen(0);

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(solution(n));
    }
}
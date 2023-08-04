package boj.dfs.p9663;

import java.io.*;

public class Main {

    static int N, result;
    static int[] board;

    private static boolean canLocate(int col) {
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

    private static void nQueen(int depth) {
        if (depth == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[depth] = i;
            if (canLocate(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p9663/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        result = 0;

        board = new int[N];

        nQueen(0);

        System.out.println(result);
    }
}

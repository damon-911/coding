package boj.simulation.p2239;

import java.io.*;

public class Main {

    static int[][] board;
    static boolean flag;

    static boolean isValid(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num || board[i][c] == num) {
                return false;
            }
        }

        int startRow = r / 3 * 3;
        int startCol = c - c % 3;

        for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
                if (board[row][col] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static void dfs(int idx) {
        if (idx == 81) {
            flag = true;
            return;
        }

        int r = idx / 9;
        int c = idx % 9;

        if (board[r][c] != 0) {
            dfs(idx + 1);
        } else {
            for (int i = 1; i < 10; i++) {
                if (!isValid(r, c, i)) {
                    continue;
                }
                board[r][c] = i;
                dfs(idx + 1);

                if (flag) {
                    return;
                }
                board[r][c] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p2239/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int[] nums : board) {
            for (int num : nums) {
                sb.append(num);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
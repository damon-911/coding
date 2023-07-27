package boj.dfs.p2580;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;

    private static boolean check(int row, int col, int value) {
        // 같은 행에 있는 원소들 중 겹치는 열 원소가 있는지 검사
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // 같은 열에 있는 원소들 중 겹치는 행 원소가 있는지 검사
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        // 3*3 칸에 중복되는 원소가 있는지 검사
        int startRow = (row / 3) * 3; // value가 속한 3x3의 행의 첫 위치
        int startCol = (col / 3) * 3; // value가 속한 3x3의 열의 첫 위치

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void sudoku(int row, int col) {
        // 해당 행이 다 채워졌을 경우 다음 행의 첫 번째 열부터 시작
        if (col == 9) {
            sudoku(row + 1, 0);
            return;
        }

        // 행과 열이 모두 채워졌을 경우 출력 후 종료
        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            return;
        }

        // 만약 해당 위치의 값이 0 이라면 1부터 9까지 중 가능한 수 탐색
        if (board[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                // i 값이 중복되지 않는지 검사
                if (check(row, col, i)) {
                    board[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            board[row][col] = 0;

            // 출력 뒤 시스템 종료
            System.exit(0);
        }

        sudoku(row, col + 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p2580/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }
}

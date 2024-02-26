package boj.simulation.p3025;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] board;
    static List<Stack<Point>> dp;

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static boolean check(int row, int col) {
        if (row >= 0 && row < R && col >= 0 && col < C) {
            return true;
        }
        return false;
    }

    static void putRock(int row, int col, int start) {
        // 돌의 아랫칸이 벽으로 막혀있거나 가장 아랫줄이 아닐 때까지 반복
        while (row < R - 1 && board[row + 1][col] != 'X') {
            if (board[row + 1][col] == 'O') {
                // 돌의 아랫칸에 돌이 있다면
                // 만약 돌의 왼쪽 칸과 왼쪽-아래 칸이 비어있다면
                if (check(row + 1, col - 1) && board[row][col - 1] == '.' && board[row + 1][col - 1] == '.') {
                    row++;
                    col--;
                }
                // 만약 돌의 오른쪽 칸과 오른쪽-아래 칸이 비어있다면
                else if (check(row + 1, col + 1) && board[row][col + 1] == '.' && board[row + 1][col + 1] == '.') {
                    row++;
                    col++;
                } else {
                    break;
                }
            } else {
                row++;
            }
            dp.get(start).push(new Point(row, col));
        }
        board[row][col] = 'O';
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p3025/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dp = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            dp.add(new Stack<>());
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int col = Integer.parseInt(br.readLine()) - 1;

            // 현재 돌을 던질 위치 c 에 해당하는 stack의 값이 이미 돌이 있다면 돌이 없는 곳까지 경로를 빼준다.
            while (!dp.get(col).isEmpty() && board[dp.get(col).peek().row][dp.get(col).peek().col] == 'O') {
                dp.get(col).pop();
            }

            // 이전에 저장된 경로가 없다면 첫 위치부터 돌 던지기를 실행
            if (dp.get(col).isEmpty()) {
                putRock(0, col, col);
            }
            // 이전에 저장된 경로가 있다면 그 곳부터 돌 던지기를 실행
            else {
                putRock(dp.get(col).peek().row, dp.get(col).peek().col, col);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                bw.write(board[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
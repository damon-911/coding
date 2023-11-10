package programmers.bruteforce_search.이차원동전뒤집기;

public class Solution {

    static int row, col;
    static int[][] B, T;
    static int answer;

    static void flipRow(int r) {
        for (int i = 0; i < col; i++) {
            B[r][i] = (B[r][i] + 1) % 2;
        }
    }

    static int compareCol(int c) {
        int check = 0;

        for (int i = 0; i < row; i++) {
            if (B[i][c] == T[i][c]) {
                check++;
            }
        }

        if (check == row)
            return 0; // 전부 일치
        else if (check == 0)
            return 1; // 전부 불일치
        else
            return -1;
    }

    static void dfs(int r, int cnt) {
        // 모든 행의 경우의 수를 확인했다면
        if (r == row) {
            boolean flag = true;

            for (int i = 0; i < col; i++) {
                int result = compareCol(i);
                if (result == -1) {
                    flag = false;
                    break;
                }
                cnt += result;
            }

            if (flag) {
                answer = Math.min(answer, cnt);
            }

            return;
        }

        flipRow(r); // 행 뒤집기
        dfs(r + 1, cnt + 1); // 행을 뒤집는 경우
        flipRow(r); // 다시 원상태로

        dfs(r + 1, cnt); // 행을 뒤집지 않는 경우
    }

    public static int solution(int[][] beginning, int[][] target) {
        answer = Integer.MAX_VALUE;

        row = beginning.length;
        col = beginning[0].length;

        B = new int[row][col];
        T = new int[row][col];

        for (int i = 0; i < row; i++) {
            B[i] = beginning[i].clone();
        }
        T = target;

        dfs(0, 0);

        if (answer == Integer.MAX_VALUE) {
            return -1;
        } else {
            return answer;
        }
    }

    public static void main(String[] args) {
        int[][] beginning = {
                { 0, 1, 0, 0, 0 },
                { 1, 0, 1, 0, 1 },
                { 0, 1, 1, 1, 0 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 1, 0 }
        };
        int[][] target = {
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1 },
                { 0, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 1 }
        };

        System.out.println(solution(beginning, target));
    }
}
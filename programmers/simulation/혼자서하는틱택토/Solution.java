package programmers.simulation.혼자서하는틱택토;

public class Solution {

    static char[][] map;

    static boolean win(char c) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (map[i][0] == c && map[i][0] == map[i][1] && map[i][1] == map[i][2])
                return true;

            // 세로
            if (map[0][i] == c && map[0][i] == map[1][i] && map[1][i] == map[2][i])
                return true;
        }

        // 대각선
        if (map[0][0] == c && map[0][0] == map[1][1] && map[1][1] == map[2][2])
            return true;

        if (map[0][2] == c && map[0][2] == map[1][1] && map[1][1] == map[2][0])
            return true;

        return false;
    }

    public static int solution(String[] board) {
        int num_O = 0;
        int num_X = 0;

        map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'O')
                    num_O++;
                else if (map[i][j] == 'X')
                    num_X++;
            }
        }

        // 1. 개수 차이 > 1
        if (num_O < num_X || num_O - num_X > 1)
            return 0;

        // 2. 둘 다 빙고
        if (win('O') && win('X'))
            return 0;

        // 3. O가 이겼다면 개수가 하나 더 많아야 하고
        if (win('O')) {
            if (num_O <= num_X)
                return 0;
        }

        // X가 이겼다면 개수가 서로 같아야 한다
        if (win('X')) {
            if (num_O != num_X)
                return 0;
        }

        return 1;
    }

    public static void main(String[] args) {
        String[] board = { "O.X", ".O.", "..X" };

        System.out.println(solution(board));
    }
}
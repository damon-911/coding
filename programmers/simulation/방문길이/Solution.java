package programmers.simulation.방문길이;

public class Solution {

    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, -1, 0, 1 };

    public static int solution(String dirs) {
        int answer = 0;

        boolean[][][] visited = new boolean[11][11][4];

        int curX = 5;
        int curY = 5;

        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int flag = 0;

            if (c == 'U')
                flag = 0;
            else if (c == 'L')
                flag = 1;
            else if (c == 'D')
                flag = 2;
            else if (c == 'R')
                flag = 3;

            int tx = curX + MX[flag];
            int ty = curY + MY[flag];

            if (tx < 0 || ty < 0 || tx >= 11 || ty >= 11)
                continue;

            if (!visited[tx][ty][flag]) {
                visited[tx][ty][flag] = true; // 현재 가는 방향
                flag = (flag % 2 == 0) ? 2 - flag : 4 - flag;
                visited[curX][curY][flag] = true; // 반대로 들어오는 방향
                answer++;
            }

            curX = tx;
            curY = ty;
        }

        return answer;
    }

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";

        System.out.println(solution(dirs));
    }
}
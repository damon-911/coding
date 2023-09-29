package programmers.simulation.카카오프렌즈컬러링북;

import java.util.Arrays;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int answer;
    static int[][] map;
    static boolean[][] visited;
    static int areaCnt = 0;

    static void dfs(int x, int y, int[][] picture) {
        if (visited[x][y])
            return;

        visited[x][y] = true;
        areaCnt++;

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || tx >= picture.length || ty < 0 || ty >= picture[0].length)
                continue;

            if (picture[x][y] == picture[tx][ty] && !visited[tx][ty]) {
                dfs(tx, ty, picture);
            }
        }
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    numberOfArea++;
                    dfs(i, j, picture);
                }

                if (areaCnt > maxSizeOfOneArea)
                    maxSizeOfOneArea = areaCnt;

                areaCnt = 0;
            }
        }

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {
                { 1, 1, 1, 0 },
                { 1, 2, 2, 0 },
                { 1, 0, 0, 1 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 3 },
                { 0, 0, 0, 3 }
        };

        System.out.println(Arrays.toString(solution(m, n, picture)));
    }
}
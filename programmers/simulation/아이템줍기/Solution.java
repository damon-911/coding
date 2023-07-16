package programmers.simulation.아이템줍기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int answer;
    static int[][] map;

    private static void bfs(int startX, int startY, int itemX, int itemY) {
        boolean[][] visited = new boolean[101][101];
        visited[startX][startY] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX);
        queue.add(startY);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = x + MX[i];
                int ty = y + MY[i];

                if (tx < 0 || tx > 100 || ty < 0 || ty > 100)
                    continue;

                if (map[tx][ty] != 1 || visited[tx][ty])
                    continue;

                // 이동한 거리 누적해서 저장
                map[tx][ty] = map[x][y] + 1;

                // 목표점 도달
                if (tx == itemX && ty == itemY) {
                    answer = Math.min(answer, map[tx][ty]);
                    continue;
                }

                visited[tx][ty] = true;
                queue.add(tx);
                queue.add(ty);
            }
        }
    }

    private static void fill(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == 2)
                    continue;

                if (i == x1 || i == x2 || j == y1 || j == y2)
                    map[i][j] = 1;
                else
                    map[i][j] = 2;
            }
        }
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = Integer.MAX_VALUE;

        // 꺾이는 코너 부분에서 쉽게 계산하기 위해 좌표 2배로 확장
        map = new int[101][101];

        // 테두리 -> 1, 내부 -> 2
        for (int i = 0; i < rectangle.length; i++) {
            fill(2 * rectangle[i][0], 2 * rectangle[i][1], 2 * rectangle[i][2], 2 * rectangle[i][3]);
        }

        bfs(2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY);

        return answer / 2;
    }

    public static void main(String[] args) {
        int[][] rectangle = {
                { 1, 1, 7, 4 },
                { 3, 2, 5, 5 },
                { 4, 3, 6, 9 },
                { 2, 6, 8, 8 }
        };
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));
    }
}
package programmers.array.거리두기확인하기;

import java.util.*;

public class Solution {

    public static final int[] MX = { 1, -1, 0, 0 };
    public static final int[] MY = { 0, 0, 1, -1 };

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean check(String[] place, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = p.x + MX[i];
                int ty = p.y + MY[i];

                if (tx < 0 || tx >= 5 || ty < 0 || ty >= 5 || (x == tx && y == ty))
                    continue;

                int dist = Math.abs(tx - x) + Math.abs(ty - y);

                if (place[tx].charAt(ty) == 'P' && dist <= 2)
                    return false;
                else if (place[tx].charAt(ty) == 'O' && dist < 2)
                    queue.offer(new Point(tx, ty));
            }
        }

        return true;
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        int index = 0;
        for (String[] place : places) {
            boolean isOk = true;
            for (int i = 0; i < 5 && isOk; i++) {
                for (int j = 0; j < 5 && isOk; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!check(place, i, j))
                            isOk = false;
                    }
                }
            }
            answer[index++] = isOk ? 1 : 0;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] places = new String[][] {
                { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
                { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
                { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" }
        };

        System.out.println(Arrays.toString(solution(places)));
    }
}
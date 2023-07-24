package programmers.kakao2023.미로탈출명령어;

public class Solution {

    static final int[] MX = { 1, 0, 0, -1 };
    static final int[] MY = { 0, -1, 1, 0 };
    static final String[] dir = { "d", "l", "r", "u" };

    static int row, col;
    static int endX, endY;
    static String answer;
    static StringBuilder result;

    private static int getDistance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }

    private static void escape(int curX, int curY, int depth, int limit) {
        // 이미 구한 경로가 있는 경우
        if (answer != null)
            return;

        // 더 이상은 도착점에 도달하지 못하는 경우
        if (depth + getDistance(curX, curY, endX, endY) > limit)
            return;

        if (depth == limit) {
            if (curX == endX && curY == endY)
                answer = result.toString();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tx = curX + MX[i];
            int ty = curY + MY[i];

            if (tx <= 0 || tx > row || ty <= 0 || ty > col)
                continue;

            result.append(dir[i]);
            escape(tx, ty, depth + 1, limit);
            result.delete(depth, depth + 1);
        }
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = null;
        result = new StringBuilder();

        row = n;
        col = m;
        endX = r;
        endY = c;

        // 두 점 사이의 거리를 구해 k보다 크거나 (k - 최단거리)가 홀수인지 확인
        // (k - 최단거리)가 짝수일 때만 거리가 k인 경로가 생긴다
        int distance = getDistance(x, y, r, c);
        if (distance > k || (k - distance) % 2 == 1)
            return "impossible";

        escape(x, y, 0, k);

        return answer != null ? answer : "impossible";
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;

        System.out.println(solution(n, m, x, y, r, c, k));
    }
}
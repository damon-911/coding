package programmers.simulation.공이동시뮬레이션;

public class Solution {

    public static long solution(int n, int m, int x, int y, int[][] queries) {
        long startRow = x;
        long endRow = x;
        long startCol = y;
        long endCol = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int step = queries[i][1];

            if (dir == 0) { // 좌
                if (startCol > 0)
                    startCol += step;
                endCol = Math.min(m - 1, endCol + step);

            } else if (dir == 1) { // 우
                if (endCol < m - 1)
                    endCol -= step;
                startCol = Math.max(0, startCol - step);

            } else if (dir == 2) { // 상
                if (startRow > 0)
                    startRow += step;
                endRow = Math.min(n - 1, endRow + step);

            } else { // 하
                if (endRow < n - 1)
                    endRow -= step;
                startRow = Math.max(0, startRow - step);
            }

            if (startRow >= n || endRow < 0 || startCol >= m || endCol < 0)
                return 0;
        }

        return (endRow - startRow + 1) * (endCol - startCol + 1);
    }

    public static void main(String[] args) {
        int n = 2;
        int m = 2;
        int x = 0;
        int y = 0;
        int[][] queries = {
                { 2, 1 },
                { 0, 1 },
                { 1, 1 },
                { 0, 1 },
                { 2, 1 }
        };

        System.out.println(solution(n, m, x, y, queries));
    }
}
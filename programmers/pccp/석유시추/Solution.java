package programmers.pccp.석유시추;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int row, col;
    static int[][] fragment;
    static boolean[][] visited;

    static int bfs(int curX, int curY, int id, int[][] land) {
        int result = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { curX, curY });

        visited[curX][curY] = true;
        fragment[curX][curY] = id;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = current[0] + MX[i];
                int ty = current[1] + MY[i];

                if (tx >= 0 && tx < row && ty >= 0 && ty < col
                        && land[tx][ty] == 1 && !visited[tx][ty]) {
                    queue.offer(new int[] { tx, ty });
                    visited[tx][ty] = true;
                    fragment[tx][ty] = id;
                    result++;
                }
            }
        }

        return result;
    }

    public static int solution(int[][] land) {
        int answer = 0;

        row = land.length;
        col = land[0].length;

        fragment = new int[row][col];
        visited = new boolean[row][col];

        int id = 0;
        Map<Integer, Integer> oilSizeMap = new HashMap<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = bfs(i, j, id, land);
                    oilSizeMap.put(id, size);
                    id++;
                }
            }
        }

        int[] oilSum = new int[col];

        for (int j = 0; j < col; j++) {
            Set<Integer> oilSizeSet = new HashSet<>();

            for (int i = 0; i < row; i++) {
                if (land[i][j] == 1) {
                    oilSizeSet.add(fragment[i][j]);
                }
            }

            for (int oilId : oilSizeSet) {
                oilSum[j] += oilSizeMap.get(oilId);
            }
        }

        for (int i = 0; i < oilSum.length; i++) {
            answer = Math.max(answer, oilSum[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] land = {
                { 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0, 1, 1, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 1, 1 }
        };

        System.out.println(solution(land));
    }
}
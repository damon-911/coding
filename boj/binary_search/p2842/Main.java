package boj.binary_search.p2842;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0, 1, 1, -1, -1 };
    static final int[] MY = { 0, 0, 1, -1, 1, -1, 1, -1 };

    static int N, result;
    static int[] start;
    static char[][] map;
    static int[][] height;
    static List<Integer> heightList;

    static int bfs(int left, int right, int target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        boolean[][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (target == 0) {
                return target;
            }

            for (int i = 0; i < 8; i++) {
                int tx = cur[0] + MX[i];
                int ty = cur[1] + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N
                        || visited[tx][ty] || height[tx][ty] < left || height[tx][ty] > right) {
                    continue;
                }

                visited[tx][ty] = true;
                if (map[tx][ty] == 'K') {
                    target--;
                }
                queue.add(new int[] { tx, ty });
            }
        }

        return target;
    }

    static void binarySearch(int min, int max, int target) {
        int bottom = 0;
        int top = heightList.indexOf(max);

        int minIdx = heightList.indexOf(min);
        int maxIdx = heightList.size();

        while (bottom <= minIdx && top < maxIdx && bottom <= top) {
            int left = heightList.get(bottom);
            int right = heightList.get(top);
            if (bfs(left, right, target) == 0) {
                result = Math.min(result, right - left);
                bottom++;
            } else {
                top++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2842/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        height = new int[N][N];

        start = new int[2];
        int target = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'P') {
                    start[0] = i;
                    start[1] = j;
                } else if (map[i][j] == 'K') {
                    target++;
                }
            }
        }

        heightList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = -1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                heightList.add(height[i][j]);
                if (map[i][j] == 'P' || map[i][j] == 'K') {
                    max = Math.max(max, height[i][j]);
                    min = Math.min(min, height[i][j]);
                }
            }
        }

        Collections.sort(heightList);

        result = Integer.MAX_VALUE;

        binarySearch(min, max, target);

        System.out.println(result);
    }
}

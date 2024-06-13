package boj.bfs.p2251;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] limit, water;
    static List<Integer> result = new ArrayList<>();

    static int[] move(int[] arr, int from, int to) {
        int[] temp = { arr[0], arr[1], arr[2] };

        if (temp[from] + arr[to] > limit[to]) {
            temp[from] -= limit[to] - arr[to];
            temp[to] = limit[to];
        } else {
            temp[from] = 0;
            temp[to] = arr[from] + arr[to];
        }

        return temp;
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0, limit[2] });

        boolean[][][] check = new boolean[201][201][201];
        check[0][0][limit[2]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == 0) {
                if (!result.contains(cur[2])) {
                    result.add(cur[2]);
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        int[] next = move(cur, i, j);
                        if (!check[next[0]][next[1]][next[2]]) {
                            check[next[0]][next[1]][next[2]] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p2251/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        limit = new int[3];
        water = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        Collections.sort(result);
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}

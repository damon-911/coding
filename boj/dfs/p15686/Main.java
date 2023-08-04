package boj.dfs.p15686;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, M;
    static int[][] map;
    static boolean[] open;

    static int result = Integer.MAX_VALUE;
    static List<int[]> house = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();

    private static void dfs(int start, int depth) {
        if (depth == M) {
            int sum = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(house.get(i)[0] - chicken.get(j)[0])
                                + Math.abs(house.get(i)[1] - chicken.get(j)[1]);
                        temp = Math.min(temp, distance);
                    }
                }
                sum += temp;
            }

            result = Math.min(result, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            dfs(i + 1, depth + 1);
            open[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    house.add(new int[] { i, j });
                else if (map[i][j] == 2)
                    chicken.add(new int[] { i, j });
            }
        }

        open = new boolean[chicken.size()];

        dfs(0, 0);

        System.out.println(result);
    }
}

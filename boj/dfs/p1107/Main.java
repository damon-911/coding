package boj.dfs.p1107;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long result = 0;
    static Set<Integer> broken = new HashSet<>();

    static void dfs(int cur, int depth) {
        if (depth > 6) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!broken.contains(i)) {
                int next = cur * 10 + i;
                int cnt = String.valueOf(next).length() + Math.abs(N - next);
                result = Math.min(result, cnt);
                dfs(next, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1107/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }

        if (N == 100) {
            System.out.println(0);
            return;
        }

        result = Math.abs(N - 100);

        dfs(0, 0);

        System.out.println(result);
    }
}
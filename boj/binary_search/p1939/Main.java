package boj.binary_search.p1939;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, start, end;
    static long max;
    static boolean[] check;
    static List<List<Bridge>> graph = new ArrayList<>();

    static class Bridge {
        int dest;
        long cost;

        public Bridge(int dest, long cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static void dfs(int cur, int target, long limit) {
        if (cur == target) {
            max = limit;
            return;
        }

        check[cur] = true;

        for (Bridge bridge : graph.get(cur)) {
            if (!check[bridge.dest] && limit <= bridge.cost) {
                dfs(bridge.dest, target, limit);
            }
        }
    }

    static void findMax(long left, long right) {
        max = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            check = new boolean[N + 1];
            dfs(start, end, mid);

            if (max != -1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            max = -1;
        }
        System.out.println(right);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p1939/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            graph.get(A).add(new Bridge(B, C));
            graph.get(B).add(new Bridge(A, C));
            max = Math.max(max, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        findMax(0, max);
    }
}
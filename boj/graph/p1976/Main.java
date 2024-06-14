package boj.graph.p1976;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x > y) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1976/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            String[] info = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (info[j].equals("1")) {
                    union(i + 1, j + 1);
                }
            }
        }

        boolean flag = true;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 1; i < M; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (find(start) == find(next)) {
                continue;
            }
            flag = false;
        }

        System.out.println(flag ? "YES" : "NO");
    }
}
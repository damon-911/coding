package boj.bfs.p11725;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<List<Integer>> tree;

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        int[] parent = new int[N + 1];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : tree.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[next] = cur;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p11725/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        bfs();
    }
}

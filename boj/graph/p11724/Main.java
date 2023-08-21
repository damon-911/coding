package boj.graph.p11724;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int count = 0;
    static boolean[] visited;
    static List<List<Integer>> graph;

    static void bfs(int cur) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(cur);

        while (!queue.isEmpty()) {
            int num = queue.poll();

            int size = graph.get(num).size();

            for (int i = 0; i < size; i++) {
                int dest = graph.get(num).get(i);

                if (visited[dest] == false) {
                    queue.offer(dest);
                    visited[dest] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11724/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }
}

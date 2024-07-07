package boj.graph.p1167;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V, node;
    static List<List<Node>> graph;
    static boolean[] visited;
    static int max = 0;

    static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static void dfs(int cur, int depth) {
        if (depth > max) {
            max = depth;
            node = cur;
        }

        visited[cur] = true;

        for (int i = 0; i < graph.get(cur).size(); i++) {
            Node next = graph.get(cur).get(i);
            if (!visited[next.dest]) {
                dfs(next.dest, next.cost + depth);
                visited[next.dest] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1167/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            while (true) {
                int dest = Integer.parseInt(st.nextToken());
                if (dest == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                graph.get(idx).add(new Node(dest, cost));
            }
        }

        // 임의의 노드(1)에서 가장 먼 노드 찾기 -> node에 저장
        visited = new boolean[V + 1];
        dfs(1, 0);

        // node에서 가장 먼 노드까지의 거리 구하기
        visited = new boolean[V + 1];
        dfs(node, 0);

        System.out.println(max);
    }
}

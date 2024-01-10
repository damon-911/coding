package boj.graph.p1068;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, root, remove;
    static int result = 0;
    static List<Node> graph = new ArrayList<>();

    static class Node {
        int idx;
        List<Node> child = new ArrayList<>();

        public Node(int idx) {
            this.idx = idx;
        }
    }

    static void removeNode(int cur) {
        for (Node child : graph.get(cur).child) {
            if (child.idx == remove) {
                graph.get(cur).child.remove(child);
                return;
            }
            removeNode(child.idx);
        }
    }

    static void dfs(int cur) {
        if (graph.get(cur).child.size() == 0) {
            result++;
            return;
        }

        for (Node child : graph.get(cur).child) {
            dfs(child.idx);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1068/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            graph.add(new Node(i));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            graph.get(parent).child.add(new Node(i));
        }

        remove = Integer.parseInt(br.readLine());

        if (remove != root) {
            removeNode(root);
            dfs(root);
        }

        System.out.println(result);
    }
}

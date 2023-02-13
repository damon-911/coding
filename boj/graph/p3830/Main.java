package boj.graph.p3830;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] root, weight;

    static void union(int a, int b, int diff) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return;

        root[bRoot] = aRoot;
        weight[bRoot] += (diff - (weight[b] - weight[a]));
    }

    static int find(int node) {
        if (root[node] == node)
            return node;
        else {
            // find 함수가 재귀적으로 돌면서 차례차례 값을 업데이트하도록 한다 ("return root[node] = find(root[node])" -> 한 번에 root에 업데이트하므로 X)
            int parent = find(root[node]);
            // weight[node] = node와 parent[node]의 무게 차이(= weight[node]) + parent[node]와 root[node]의 무게 차이(= weight[root[node]])
            weight[node] += weight[root[node]];
            return root[node] = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;

            root = new int[N + 1];
            for (int i = 1 ; i <= N; i++)
                root[i] = i;

            weight = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String flag = st.nextToken();

                if (flag.equals("!")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int diff = Integer.parseInt(st.nextToken());

                    union(a, b, diff);
                }
                else if (flag.equals("?")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if (find(a) != find(b))
                        System.out.println("UNKNOWN");
                    else
                        System.out.println(weight[b] - weight[a]);
                }
            }
        }
    }
}

package boj.graph.p1717;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    static int find(int element) {
        if (parent[element] == element)
            return element;
        else {
            return parent[element] = find(parent[element]);
        }
    }

    static void union(int element1, int element2) {
        // element1과 element2의 대표 노드 확인
        int root1 = find(element1);
        int root2 = find(element2);

        // element1과 element2의 대표 노드가 다를 경우
        if (root1 != root2)
            parent[root1] = root2;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int num = 1; num <= N; num++) {
            parent[num] = num;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int flag = Integer.parseInt(st.nextToken());
            int element1 = Integer.parseInt(st.nextToken());
            int element2 = Integer.parseInt(st.nextToken());

            if (flag == 0) {
                union(element1, element2);
            }
            else if (flag == 1) {
                if (find(element1) == find(element2)) {
                    System.out.println("YES");
                }
                else
                    System.out.println("NO");
            }
        }
    }
}

package boj.graph.p1717;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    static void union(int a, int b) {
        // element1과 element2의 대표 노드 확인
        int root1 = find(a);
        int root2 = find(b);

        // element1과 element2의 대표 노드가 다를 경우
        if (root1 != root2)
            parent[root1] = root2;
    }

    static int find(int a) {
        // 찾는 대상과 인덱스 번호가 같다면 그 인덱스가 해당 집합의 부모이다
        if (parent[a] == a)
            return a;
        else {
            // 해당 인덱스가 가리키는 값(부모 노드)을 따라 최종 부모 노드까지 탐색
            return parent[a] = find(parent[a]);
        }
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (flag == 0) {
                union(a, b);
            }
            else if (flag == 1) {
                if (find(a) == find(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }
}

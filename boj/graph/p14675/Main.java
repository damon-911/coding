package boj.graph.p14675;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p14675/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) {
                // 루트 노드 또는 리프 노드를 제외한 경우에 두 개의 트리로 나눠진다.
                int count = adjList.get(k).size();
                if (count == 1)
                    System.out.println("no");
                else
                    System.out.println("yes");
            }
            else {
                // 항상 두 개의 트리로 나눠진다.
                System.out.println("yes");
            }
        }
    }
}

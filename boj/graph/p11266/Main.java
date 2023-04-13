package boj.graph.p11266;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<ArrayList<Integer>> adjList;
    static int[] visited;
    static int visitOrder;
    static boolean[] isCutPoint;
    static int cutPointCnt;

    // DFS 수행부
    static int cutPointSearch(int curNode, int root) {
        // 루트 노드일 때 자식 노드가 몇 개인지 파악
        int childNodeCnt = 0;

        // 방문 순서
        visited[curNode] = visitOrder++;

        // 방문한 점의 인접한 minOrder
        // 초기화는 자기 자신의 순서
        int minOrder = visited[curNode];

        // current Node와 인접한 인접리스트 탐색 진행
        for (int i = 0; i < adjList.get(curNode).size(); i++) {
            int destNode = adjList.get(curNode).get(i);

            // 만일 방문을 한 곳이라면
            if (visited[destNode] != 0) {
                minOrder = Math.min(minOrder, visited[destNode]);
                continue;
            }

            // 자식 Order는 DFS를 한 번 더 돌린다.
            int childOrder = cutPointSearch(destNode, 1);

            // 루트노드가 아닌 경우
            // 자기 자신의 order와 자기 자신과 인접한 정점들의 order 중 가장 작은 order
            if (root == 1 && childOrder >= visited[curNode]) {
                if (!isCutPoint[curNode])
                    cutPointCnt++;
                isCutPoint[curNode] = true;
            }

            minOrder = Math.min(minOrder, childOrder);
            childNodeCnt++;
        }

        if (root == 0 && childNodeCnt > 1) {
            if (!isCutPoint[curNode])
                cutPointCnt++;
            isCutPoint[curNode] = true;
        }

        return minOrder;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11266/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 방문 순서 체크
        visited = new int[V + 1];
        // 단절점인지 체크
        isCutPoint = new boolean[V + 1];

        adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        // a와 b를 입력받아서 인접리스트를 만들어준다
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }

        visitOrder = 1;

        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0)
                cutPointSearch(i, 0);
        }

        System.out.println(cutPointCnt);
        for (int i = 1; i <= V; i++) {
            if (isCutPoint[i])
                System.out.print(i + " ");
        }
    }
}

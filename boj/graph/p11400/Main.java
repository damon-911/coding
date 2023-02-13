package boj.graph.p11400;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<CutLine> cutList;
    static int[] visited;
    static boolean[] isCutEdge;
    static int cutEdgeCnt, visitOrder;

    static class CutLine implements Comparable<CutLine>{
        int startNode;
        int endNode;

        public CutLine(int startNode, int endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }

        @Override
        public int compareTo(CutLine c1) {
            int comp = this.startNode - c1.startNode;

            if (comp == 0) {
                return this.endNode - c1.endNode;
            }
            else
                return comp;
        }
    }

    static int cutEdgeSearch(int curNode, int parNode) {
        // 자신의 방문 순서를 기록
        visited[curNode] = visitOrder++;

        // 방문한 점의 인접한 minOrder
        int minOrder = visited[curNode];

        for (int i = 0; i < adjList.get(curNode).size(); i++) {
            int destNode = adjList.get(curNode).get(i);

            // 만일 방문을 한 곳이라면
            if (visited[destNode] != 0) {
                // 해당 간선이 이전(부모)으로 가는 간선이 아니라면
                if (destNode != parNode) {
                    minOrder = Math.min(minOrder, visited[destNode]);
                }
                continue;
            }

            // DFS를 진행하여 자식 노드들의 minOrder(low)를 가져온다
            int childOrder = cutEdgeSearch(destNode, curNode);

            // 자신의 순서(order) < low 라고 한다면 이는 단절선
            if (childOrder > visited[curNode]) {
                int a = curNode;
                int b = destNode;

                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                cutList.add(new CutLine(a, b));
            }

            minOrder = Math.min(minOrder, childOrder);
        }

        return minOrder;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11400/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        cutList = new ArrayList<>();

        // 방문 순서 체크
        visited = new int[V + 1];

        // 단절선 여부
        isCutEdge = new boolean[V + 1];

        cutEdgeCnt = 0;
        visitOrder = 1;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0)
                cutEdgeSearch(i, 0);
        }

        Collections.sort(cutList);

        System.out.println(cutList.size());
        for (CutLine cutLine : cutList) {
            System.out.println(cutLine.startNode + " " + cutLine.endNode);
        }
    }
}

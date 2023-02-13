package boj.graph.p11438;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> adjList;
    static int[][] parent;
    static boolean[] check;
    static int[] depth;

    static void getDepth(int cur, int dep) {
        // 한번 방문한 곳은 방문하지 않음
        check[cur] = true;
        // 현재 정점에 depth를 저장한다
        depth[cur] = dep;

        // 인접리스트 (adjList.get(cur).size() -> cur 점의 degree)
        for (int i = 0; i < adjList.get(cur).size(); i++) {
            int dest = adjList.get(cur).get(i);

            // 방문하지 않은 점일 경우
            if (!check[dest]) {
                // dest 점의 직계조상은 cur이다
                parent[0][dest] = cur;

                // DFS 진행
                getDepth(dest, dep + 1);
            }
        }
    }

    static void LCA(int a, int b) {
        // a와 b의 depth를 저장해놓는다
        int dep_a = depth[a];
        int dep_b = depth[b];

        // a를 감소시키면서 계산 진행할 것
        // depth가 큰 것을 a로 위치시킨다
        if (dep_a < dep_b) {
            int temp = dep_a;
            dep_a = dep_b;
            dep_b = temp;

            temp = a;
            a = b;
            b = temp;
        }

        // a와 b의 depth를 맞춘다
        // a의 depth = 13, b의 depth = 2
        // j = 3 -> 13, 2
        // 2^3 = 8 -> (13 - 8) >= 2 (8만큼 더 올라갈 수 있음)
        // j = 2 -> 5, 2
        // 2^2 = 4 -> (5 - 4) >= 2
        // j = 1 -> 5, 2
        // 2^1 = 2 -> (5 - 2) >= 2 (2만큼 더 올라갈 수 있음)
        // j = 0 -> 3, 2
        // 2^0 = 1 -> (3 - 1) >= 2 (1만큼 더 올라갈 수 있음)
        for (int i = 17; i >= 0; i--) {
            // Math.pow(a, b) -> a^b
            // 2^i 를 가져온다
            // "a의 depth - 2^i >= b의 depth" 보다 클 경우
            if (dep_a - (int) Math.pow(2, i) >= dep_b) {
                // depth 조절
                dep_a -= (int) Math.pow(2, i);
                // a의 j번째 조상
                a = parent[i][a];

                if (dep_a == dep_b)
                    break;
            }
        }

        int answer = a;

        // 두 a, b를 같이 올리는 과정
        // depth를 7이라고 가정
        // a != b, dep_a = 7, dep_b = 7
        // 2^3 = 8
        if (a != b) {
            for (int i = 17; i >= 0; i--) {
                if (parent[i][a] != parent[i][b]) {
                    // a의 2^i번째 조상과 b의 2^i번째 조상이 같다면 a와 b를 올린다
                    a = parent[i][a];
                    b = parent[i][b];
                }

                // 올라가다 보면 LCA에 도달한다
                answer = parent[i][a];
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        check = new boolean[N + 1];
        depth = new int[N + 1];
        parent = new int[18][N + 1];

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
        }
        
        // depth를 구하는 탐색
        getDepth(1, 0);

//        // 필요한 parent 크기 구할 때
//        int count = 0;
//        int num = 1;
//        while (num < N) {
//            num *= 2;
//            count++;
//        }

        // 2^17까지 조상을 구해놓는다면 배열 값이 부족할 일이 없음 (2^17 > N = 100,000)
        for (int i = 1; i <= 17; i++) {
            for (int j = 1; j <= N; j++) {
                // j의 2^i번째 부모는 j의 2^(i-1)번째 부모의 2^(i-1)번째 부모이다
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            LCA(a, b);
        }
    }
}

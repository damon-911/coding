package boj.graph.p3176;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static ArrayList<ArrayList<Road>> adjList;
    static int[][] parent, maxRoad, minRoad;
    static int[] depth;
    static int count;
    static int max, min;

    static class Road {
        int dest;
        int length;

        public Road(int dest, int length) {
            this.dest = dest;
            this.length = length;
        }
    }

    static void getDepth(int cur, int dep) {
        // 현재 정점에 depth를 저장한다
        depth[cur] = dep;

        // adjList.get(cur).size() -> cur 점의 degree
        int len = adjList.get(cur).size();

        // 인접리스트 탐색
        for (int i = 0; i < len; i++) {
            int dest = adjList.get(cur).get(i).dest;
            int length = adjList.get(cur).get(i).length;

            // 방문하지 않은 점일 경우
            if (depth[dest] == 0) {
                // DFS 진행
                getDepth(dest, dep + 1);

                // dest 점의 직계조상은 cur이다
                parent[0][dest] = cur;

                // 현재 length 갱신
                minRoad[0][dest] = length;
                maxRoad[0][dest] = length;
            }
        }
    }

    static void LCA(int a, int b) {
        // a를 감소시키면서 계산 진행할 것
        // depth가 큰 것을 a로 위치시킨다
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        min = Integer.MAX_VALUE;
        max = -1;

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
        for (int i = count - 1; i >= 0; i--) {
            // Math.pow(a, b) -> a^b
            // 2^j 를 가져온다
            // "a의 depth - 2^i >= b의 depth" 보다 클 경우
            if (depth[a] - (int) Math.pow(2, i) >= depth[b]) {
                min = Math.min(min, minRoad[i][a]);
                max = Math.max(max, maxRoad[i][a]);

                // a의 j번째 조상
                a = parent[i][a];
            }
        }

        // depth를 맞췄는데 같다면 종료
        if (a == b)
            return;

        // 두 a, b를 같이 올리는 과정
        // depth를 7이라고 가정
        // a != b, dep_a = 7, dep_b = 7
        // 2^3 = 8
        for (int i = count - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                min = Math.min(min, Math.min(minRoad[i][a], minRoad[i][b]));
                max = Math.max(max, Math.max(maxRoad[i][a], maxRoad[i][b]));

                // a의 2^i번째 조상과 b의 2^i번째 조상이 같다면 a와 b를 올린다
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        min = Math.min(min, Math.min(minRoad[0][a], minRoad[0][b]));
        max = Math.max(max, Math.max(maxRoad[0][a], maxRoad[0][b]));
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p3176/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 필요한 parent 크기 구할 때
        count = 0;
        int num = 1;
        while (num < N) {
            num *= 2;
            count++;
        }

        depth = new int[N + 1];
        parent = new int[count][N + 1];

        maxRoad = new int[count][N + 1];
        minRoad = new int[count][N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adjList.get(A).add(new Road(B, C));
            adjList.get(B).add(new Road(A, C));
        }

        getDepth(1, 1);

        for (int i = 1; i < count; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];

                // 현재 노드에서 2^i번째 조상까지의 최대 거리와 최소 거리
                maxRoad[i][j] = Math.max(maxRoad[i - 1][j], maxRoad[i - 1][parent[i - 1][j]]);
                minRoad[i][j] = Math.min(minRoad[i - 1][j], minRoad[i - 1][parent[i - 1][j]]);
            }
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            LCA(D, E);

            System.out.println(min + " " + max);
        }
    }
}

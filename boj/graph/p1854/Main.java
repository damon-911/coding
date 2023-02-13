package boj.graph.p1854;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static List<List<Road>> path;
    static List<PriorityQueue<Integer>> pathPQ;
    static PriorityQueue<Road> pq;

    static class Road implements Comparable<Road> {
        int dest;
        int time;

        public Road(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }

    static void findMinLength() {
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            int RD = road.dest;
            int RT = road.time;

            int len = path.get(RD).size();

            // 인접 리스트로 비교
            for (int i = 0; i < len; i++) {
                int nextRD = path.get(RD).get(i).dest;
                int nextRT = path.get(RD).get(i).time;

                // pathPQ[끝점과 인접한 점]의 사이즈 < K (K번째 최단경로의 소요시간)
                if (pathPQ.get(nextRD).size() < K) {
                    pathPQ.get(nextRD).offer(nextRT + RT);
                    pq.offer(new Road(nextRD, nextRT + RT));
                }
                else {
                    // pathPQ[끝점과 인접한 점] 하나를 빼면
                    // nextRT + RT -> 현재 계산한 최단경로의 소요시간
                    // peek라는 건 priorty queue의 제일 최상위 노드를 가져옴
                    // 최소힙이라면(오름차순 priority queue) 최소값
                    // 최대힙이라면(내림차순 priority queue) 최대값
                    // 오름차순으로 만들어 놓으면
                    // K = 2
                    // 7 priority queue > 7
                    // 9 priority queue > 9 7 >> 2번째 최단경로를 9을 만들어 놓았는데
                    // 8 priority queue > 8 7 >> 9를 제거하고 8로 갱신하여 준다
                    // K번째 최단 경로는 항상 pathPQ.get(nextRD)의 top으로 유지가 된다
                    if (pathPQ.get(nextRD).peek() > nextRT + RT) {
                        // pathPQ의 제일 윗값 (max 값을 제거)
                        pathPQ.get(nextRD).poll();

                        // pathPQ에 신규 값을 넣어준다
                        pathPQ.get(nextRD).offer(nextRT + RT);
                        pq.offer(new Road(nextRD, nextRT + RT));
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (pathPQ.get(i).size() != K)
                System.out.println(-1);
            else
                System.out.println(pathPQ.get(i).peek());
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1854/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        path = new ArrayList<>();
        pathPQ = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            path.add(new ArrayList<>());
            pathPQ.add(new PriorityQueue<>(Collections.reverseOrder()));    // 역순 - 내림차순
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            path.get(A).add(new Road(B, C));
        }

        pq = new PriorityQueue<>();

        pq.offer(new Road(1, 0));
        pathPQ.get(1).offer(0);

        findMinLength();
    }
}

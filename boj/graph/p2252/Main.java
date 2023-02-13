package boj.graph.p2252;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] students;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> queue;

    static void compare() {
        for (int i = 1; i <= N; i++) {
            if (students[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 큐에 들어있는 값 출력
            int out = queue.poll();
            System.out.print(out + " ");

            // 큐에서 나온 값 -> 현재 indegree가 0인 정점
            // 해당 정점과 연결된 다른 정점들의 indegree를 1씩 감소시킴
            for (int i = 0; i < graph.get(out).size(); i++) {
                int dest = graph.get(out).get(i);

                // indegree를 1씩 감소시킨 각 정점이 indegree = 0 이라면 큐에 넣는다
                if (--students[dest] == 0)
                    queue.offer(dest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p2252/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new int[N + 1];

        // 그래프의 연결관계를 저장할 인접 리스트
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        queue = new LinkedList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());

            graph.get(student1).add(student2);

            // indegree 값 조정
            students[student2]++;
        }

        compare();
    }
}

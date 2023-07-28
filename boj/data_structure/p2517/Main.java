package boj.data_structure.p2517;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static List<Runner> runners;
    static int[] tree, result;

    static class Runner {
        int idx;
        int skill;

        Runner(int idx, int skill) {
            this.idx = idx;
            this.skill = skill;
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft)
            return 0;

        if (queryLeft <= left && right <= queryRight)
            return tree[node];

        int mid = (left + right) / 2;

        return query(left, mid, node * 2, queryLeft, queryRight)
                + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);

    }

    static int update(int left, int right, int node, int idx) {
        if (idx < left || right < idx)
            return tree[node];

        if (left == right)
            return tree[node] += 1;

        int mid = (left + right) / 2;

        return tree[node] = update(left, mid, node * 2, idx) + update(mid + 1, right, node * 2 + 1, idx);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p2517/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        runners = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            runners.add(new Runner(i, Integer.parseInt(br.readLine())));
        }

        // skill을 기준으로 오름차순으로 정렬
        Collections.sort(runners, (r1, r2) -> r1.skill - r2.skill);

        // skill 크기 순으로 정렬되어 있기 때문에 각 원소의 skill을 1 ~ N으로 설정 -> 인덱스 트리 사용하기 위해서
        for (int i = 0; i < N; i++) {
            Runner runner = runners.get(i);
            runner.skill = i + 1;
        }

        // idx 기준으로 오름차순으로 정렬
        Collections.sort(runners, (r1, r2) -> (r1.idx - r2.idx));

        // 인덱스 트리의 리프 노드 수 파악
        int S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new int[S * 2];

        StringBuilder sb = new StringBuilder();

        for (Runner r : runners) {
            sb.append(r.idx - query(1, N, 1, 1, r.skill) + "\n");
            update(1, N, 1, r.skill);
        }

        System.out.println(sb.toString());
    }
}

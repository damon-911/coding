package boj.data_structure.p2042;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static long[] nums;
    static long[] tree;

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = nums[start];
        }

        int mid = (start + end) / 2;
        // 왼쪽 자식(2 * node) 합 + 오른쪽 자식(2 * node + 1) 합
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static long query(int start, int end, int node, int queryLeft, int queryRight) {
        // 1. 연관 없음
        if (queryRight < start || end < queryLeft) {
            return 0;
        }

        // 2. 판단 가능 (쏙 들어있음)
        if (queryLeft <= start && end <= queryRight) {
            return tree[node];
        }

        // 3. 판단 불가 (걸쳐 있음)
        int mid = (start + end) / 2;
        return query(start, mid, node * 2, queryLeft, queryRight) + query(mid + 1, end, node * 2 + 1, queryLeft, queryRight);
    }

    static void update(int start, int end, int node, int target, long diff) {
        // 1. 연관 없음
        if (target < start || end < target)
            return;

        // 2. 연관 있음
        tree[node] += diff;

        // 아직 탐색할 수 있는 범위가 더 있다면 하위에 있는 노드도 바꿔줌
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, target, diff);
            update(mid + 1, end, node * 2 + 1, target, diff);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];

        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());

            if (flag == 1) {
                int value1 = Integer.parseInt(st.nextToken());
                long value2 = Long.parseLong(st.nextToken());
                long diff = value2 - nums[value1];
                nums[value1] = value2;
                update(1, N, 1, value1, diff);
            }
            else {
                int value1 = Integer.parseInt(st.nextToken());
                int value2 = Integer.parseInt(st.nextToken());
                System.out.println(query(1, N, 1, value1, value2));
            }
        }
    }
}

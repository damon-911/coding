package boj.data_structure.p1275;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static long[] nums;
    static long[] tree;

    static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = nums[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static long query(int start, int end, int node, int queryLeft, int queryRight) {
        if (queryRight < start || end < queryLeft) {
            return 0;
        }

        if (queryLeft <= start && end <= queryRight) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(start, mid, node * 2, queryLeft, queryRight)
                + query(mid + 1, end, node * 2 + 1, queryLeft, queryRight);
    }

    static void update(int start, int end, int node, int target, long diff) {
        if (target < start || end < target) {
            return;
        }

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, target, diff);
            update(mid + 1, end, node * 2 + 1, target, diff);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p1275/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nums = new long[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        tree = new long[N * 4];

        init(1, N, 1);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (left <= right) {
                System.out.println(query(1, N, 1, left, right));
            } else {
                System.out.println(query(1, N, 1, right, left));
            }

            update(1, N, 1, target, value - nums[target]);
            nums[target] = value;
        }
    }
}
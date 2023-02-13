package boj.data_structure.p2243;

import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int max = 1000000;
    static long[] tree;

    static int query(int left, int right, int node, long target) {
        // 리프 노드인 경우 (사탕 찾음)
        if (left == right) {
            return left;
        }

        // 내부 노드인 경우
        int mid = (left + right) / 2;
        if (tree[node * 2] >= target) { // 왼쪽 노드이면 target
            return query(left, mid, node * 2, target);
        }
        else {  // 오른쪽 노드이면 (target - 왼쪽노드)
            return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
        // 1. 연관 없음
        if (target < left || right < target) {
            return;
        }

        // 2. 연관 있음
        tree[node] += diff;

        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < max) {
            S *= 2;
        }

        tree = new long[S * 2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());

            if (flag == 1) {
                long target = Integer.parseInt(st.nextToken());
                int answer = query(1, max, 1, target);  // 꺼낸 사탕의 종류
                System.out.println(answer);
                update(1, max, 1, answer, -1);
            }
            else {
                int taste = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                update(1, max, 1, taste, num);
            }
        }
    }
}

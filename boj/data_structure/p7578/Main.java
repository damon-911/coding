package boj.data_structure.p7578;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;

    static void updateTree(int start, int end, int node, int idx, int dif) {
        if (idx < start || idx > end) {
            return;
        }

        tree[node] += dif;

        if (start != end) {
            int mid = (start + end) / 2;
            updateTree(start, mid, node * 2, idx, dif);
            updateTree(mid + 1, end, node * 2 + 1, idx, dif);
        }
    }

    static long countOverlap(int start, int end, int left, int right, int node) {
        if (end < left || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return countOverlap(start, mid, left, right, node * 2) + countOverlap(mid + 1, end, left, right, node * 2 + 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p7578/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        tree = new long[N * 4];

        int[] A = new int[N];

        // (식별번호, 인덱스)
        Map<Integer, Integer> B = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B.put(Integer.parseInt(st.nextToken()), i);
        }

        long result = 0;

        for (int i = 0; i < N; i++) {
            int cur = A[i];
            int idx = B.get(cur);

            // idx보다 큰 인덱스 중에 이미 방문한 적 있는 인덱스의 개수를 구함
            result += countOverlap(1, N, idx + 1, N, 1);

            // 해당 idx를 방문했다는 의미로 idx가 포함된 구간합에 모두 1씩 더함
            updateTree(1, N, 1, idx, 1);
        }

        System.out.println(result);
    }
}

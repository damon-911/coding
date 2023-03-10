package boj.data_structure;

public class IndexedTree {

    static int N, S;
    static long[] nums;
    static long[] tree;

    static long init(int left, int right, int node) {
        // 리프 노드일 경우
        if (left == right) {
            if (left <= N)
                tree[node] = nums[left];
            else
                tree[node] = 0;
        }
        // 내부 노드일 경우
        else {
            int mid = (left + right) / 2;
            long leftResult = init(left, mid, node * 2);
            long rightResult = init(mid + 1, right, node * 2 + 1);
            tree[node] = leftResult + rightResult;
        }

        return tree[node];
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 연관 없음
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 2. 판단 가능 (쏙 들어있음)
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 3. 판단 불가 (걸쳐 있음)
        else {
            int mid = (left + right) / 2;
            long leftResult = query(left, mid, node * 2, queryLeft, queryRight);
            long rightResult = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftResult + rightResult;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 1. 연관 없음
        if (target < left || right < target)
            return;
        // 2. 연관 있음
        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static void initBU() {
        // 자식 노드는 데이터로 채움
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i];
        }

        // 내부 노드는 자식의 합으로 채움
        for (int i = S - 1; i >= 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long queryBU(int queryLeft, int queryRight) {
        long sum = 0;
        int left = S + queryLeft - 1;
        int right = S + queryRight - 1;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }

    static void updateBU(int target, int value) {
        int node = S + target - 1;
        tree[node] = value;
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    public static void main(String[] args) {
        N = 8;
        nums = new long[] { 0, 3, 2, 4, 5, 1, 6, 2, 7 };

        S = 1;
        while (S < N) {
            S *= 2;
        }

        tree = new long[S * 2];

        long answer = init(1, S, 1);
        System.out.println(answer);
    }
}

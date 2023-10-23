package programmers.bruteforce_search.외벽점검;

public class Solution {

    static int[][] weakCases;
    static int answer;

    static void check(int[] distCase, int[] weakCase) {
        int cur = 0;
        int next = 0;
        int count = 0;

        while (cur < weakCase.length && count < distCase.length) {
            next = cur + 1;
            while (next < weakCase.length && weakCase[cur] + distCase[count] >= weakCase[next]) {
                next++;
            }
            cur = next;
            count++;
        }

        if (cur == weakCase.length && count < answer)
            answer = count;
    }

    static void makeDistCase(int[] dist, int[] distCase, boolean[] visited, int depth) {
        if (depth == dist.length) {
            for (int[] weakCase : weakCases)
                check(distCase, weakCase);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                distCase[depth] = dist[i];
                makeDistCase(dist, distCase, visited, depth + 1);
                distCase[depth] = 0;
                visited[i] = false;
            }
        }
    }

    static void makeWeakCase(int n, int[] weak) {
        int[] temp = weak.clone();
        weakCases[0] = temp.clone();

        for (int i = 1; i < weak.length; i++) {
            int num = temp[0];
            for (int j = 1; j < weak.length; j++) {
                temp[j - 1] = temp[j];
            }
            temp[weak.length - 1] = num + n;
            weakCases[i] = temp.clone();
        }
    }

    public static int solution(int n, int[] weak, int[] dist) {
        answer = dist.length + 1;

        weakCases = new int[weak.length][weak.length];

        makeWeakCase(n, weak);

        makeDistCase(dist, new int[dist.length], new boolean[dist.length], 0);

        if (answer == dist.length + 1)
            return -1;
        else
            return answer;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = { 1, 5, 6, 10 };
        int[] dist = { 1, 2, 3, 4 };

        System.out.println(solution(n, weak, dist));
    }
}
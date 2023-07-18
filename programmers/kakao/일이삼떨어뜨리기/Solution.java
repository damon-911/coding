package programmers.kakao.일이삼떨어뜨리기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    static int[] answer;
    static List<Integer>[] tree;

    private static void game(int N, int[][] edges, int[] target) {
        int num = 0;

        // 현재 노드를 몇 번 지나갔는지 저장
        int[] pass = new int[N];

        // 현재 노드가 보유하고 있는 숫자 개수 저장
        int[] count = new int[N];

        // 체크한 노드인지 저장
        boolean[] checked = new boolean[N];

        // 리프 노드 저장
        ArrayList<Integer> leaf = new ArrayList<>();

        // 리프 노드이고 타겟의 수가 0보다 크다면 케이스에 추가
        for (int i = 0; i < N; i++) {
            if (tree[i].isEmpty() && target[i] > 0)
                num++;
        }

        while (num > 0) {
            int node = 0;

            // 리프 노드까지 접근
            // 간선을 (현재 노드를 지난 횟수 % 자식 노드의 개수) 값으로 설정
            while (tree[node].size() > 0)
                node = tree[node].get(pass[node]++ % tree[node].size());

            // 리프 노드에 떨어진 숫가 개수 증가
            count[node]++;

            // 리프 노드 저장
            leaf.add(node);

            // 현재 노드가 보유하고 있는 숫자 개수 > 노드의 target 값 이라면
            // 모든 수를 1로 변경해도 수를 만족시킬 수 없으므로 -1 리턴
            if (count[node] > target[node]) {
                answer = new int[] { -1 };
                return;
            }

            // 방문하지 않은 리프 노드이고
            // 노드의 target 값 ≤ 현재 노드가 보유하고 있는 숫자 개수 * 3 이라면
            if (!checked[node] && target[node] <= 3 * count[node]) {
                checked[node] = true;
                num--;
            }
        }

        // 리프 노드 탐색하면서 1, 2, 3을 작은 순부터 대입
        ArrayList<Integer> result = new ArrayList<>();
        for (int lf : leaf) {
            count[lf]--;
            for (int value = 1; value <= 3; value++) {
                int temp = target[lf] - value;
                if (count[lf] <= temp && temp <= 3 * count[lf]) {
                    result.add(value);
                    target[lf] -= value;
                    break;
                }
            }
        }

        // List -> Array
        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
    }

    public static int[] solution(int[][] edges, int[] target) {
        int N = edges.length + 1;

        tree = new ArrayList[N + 1];

        for (int i = 0; i < N; i++)
            tree[i] = new ArrayList<>();

        for (int[] edge : edges)
            tree[edge[0] - 1].add(edge[1] - 1);

        for (int i = 0; i < N; i++)
            Collections.sort(tree[i]);

        game(N, edges, target);

        return answer;
    }

    public static void main(String[] args) {
        int[][] edges = {
                { 2, 4 },
                { 1, 2 },
                { 6, 8 },
                { 1, 3 },
                { 5, 7 },
                { 2, 5 },
                { 3, 6 },
                { 6, 10 },
                { 6, 9 }
        };
        int[] target = { 0, 0, 0, 3, 0, 0, 5, 1, 2, 3 };

        System.out.println(Arrays.toString(solution(edges, target)));
    }
}
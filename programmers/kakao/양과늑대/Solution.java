package programmers.kakao.양과늑대;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static List<Integer>[] childs;
    static int[] sheepInfo;
    static int answer = 0;

    private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextList) {
        // 늑대/양 수, 양의 최대값 최신화
        if (sheepInfo[idx] == 0)
            sheepCnt++;
        else
            wolfCnt++;

        if (wolfCnt >= sheepCnt)
            return;

        answer = Math.max(sheepCnt, answer);

        // 다음 탐색 위치 갱신
        List<Integer> list = new ArrayList<>();
        list.addAll(nextList);

        // 다음 탐색 목록 중 현재 위치 제외
        list.remove(Integer.valueOf(idx));

        if (childs[idx] != null) {
            for (int child : childs[idx]) {
                list.add(child);
            }
        }

        // 갈 수 있는 모든 노드 dfs 수행
        for (int nextIdx : list) {
            dfs(nextIdx, sheepCnt, wolfCnt, list);
        }
    }

    public static int solution(int[] info, int[][] edges) {
        sheepInfo = info;
        childs = new ArrayList[info.length];

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (childs[parent] == null) {
                childs[parent] = new ArrayList<>();
            }

            childs[parent].add(child);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);

        dfs(0, 0, 0, list);

        return answer;
    }

    public static void main(String[] args) {
        int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
        int[][] edges = {
                { 0, 1 },
                { 1, 2 },
                { 1, 4 },
                { 0, 8 },
                { 8, 7 },
                { 9, 10 },
                { 9, 11 },
                { 4, 3 },
                { 6, 5 },
                { 4, 6 },
                { 8, 9 }
        };

        System.out.println(solution(info, edges));
    }
}
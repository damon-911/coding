package programmers.hash.후보키;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    static String[][] g_relation;
    static Set<String> set;

    public static boolean isPossible(String cols, boolean[] selected) {
        // 최소성이 만족되는지 확인
        // cols 안에 set에 들어있는 `후보키가 가능한 colume들의 집합의 요소들`이 모두 포함되어있는지 확인
        for (String s : set) {
            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                if (!cols.contains(s.charAt(i) + "")) {
                    flag = false;
                }
            }

            if (flag) {
                return false;
            }
        }

        // 몇 번째 colume들을 확인해야 하는지 처리
        int[] col_name = new int[cols.length()];
        int idx = 0;

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                col_name[idx++] = i;
            }
        }

        // 값의 중복이 있는지 확인. 중복된 값이 있다면 후보키로 사용될 수 없음
        Set<String> values = new HashSet<>();
        String value = "";

        for (int i = 0; i < g_relation.length; i++) {
            value = "";

            for (int j = 0; j < col_name.length; j++) {
                value += g_relation[i][col_name[j]];
            }

            if (values.contains(value)) {
                return false;
            } else {
                values.add(value);
            }
        }

        return true;
    }

    static void dfs(int idx, int cnt, int max, boolean[] selected) {
        if (cnt == max) {
            String cols = "";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    cols += String.valueOf(i);
                }
            }

            if (isPossible(cols, selected)) {
                set.add(cols);
            }

            return;
        }

        if (idx >= selected.length)
            return;

        selected[idx] = true;
        dfs(idx + 1, cnt + 1, max, selected);

        selected[idx] = false;
        dfs(idx + 1, cnt, max, selected);
    }

    public static int solution(String[][] relation) {
        g_relation = relation;
        set = new HashSet<>();

        // 선택할 튜플 갯수
        for (int i = 1; i <= relation[0].length; i++) {
            boolean[] selected = new boolean[relation[0].length];
            dfs(0, 0, i, selected);
        }

        return set.size();
    }

    public static void main(String[] args) {
        String[][] relation = {
                { "100", "ryan", "music", "2" },
                { "200", "apeach", "math", "2" },
                { "300", "tube", "computer", "3" },
                { "400", "con", "computer", "4" },
                { "500", "muzi", "music", "3" },
                { "600", "apeach", "music", "2" }
        };

        System.out.println(solution(relation));
    }
}
package programmers.sorting.실패율;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    public static int[] solution(int N, int[] stages) {
        Map<Integer, Double> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            double total = 0;
            double fail = 0;

            for (int j = 0; j < stages.length; j++) {
                if (i <= stages[j])
                    total++;

                if (i == stages[j])
                    fail++;
            }

            if (total == 0 && fail == 0)
                map.put(i, 0.0);
            else
                map.put(i, fail / total);
        }

        // 실패율을 기준으로 스테이지 번호 내림차순 정렬
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> Double.compare(map.get(o2), map.get(o1)));

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int N = 5;
        int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };

        System.out.println(Arrays.toString(solution(N, stages)));
    }
}
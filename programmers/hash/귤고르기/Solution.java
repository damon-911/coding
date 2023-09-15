package programmers.hash.귤고르기;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Solution {

    public static int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        List<Integer> keylist = new ArrayList<>(map.keySet());
        Collections.sort(keylist, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });

        for (Integer num : keylist) {
            if (k <= 0)
                break;
            answer++;
            k -= map.get(num);
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = { 1, 3, 2, 5, 4, 5, 2, 3 };

        System.out.println(solution(k, tangerine));
    }
}
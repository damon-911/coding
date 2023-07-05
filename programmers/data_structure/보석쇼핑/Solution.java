package programmers.data_structure.보석쇼핑;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (String str : gems) {
            set.add(str);
        }

        int distance = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        int start = 0;
        int end = 0;

        while (true) {
            if (set.size() == map.size()) {
                map.put(gems[left], map.get(gems[left]) - 1);

                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            } else if (right == gems.length) {
                break;
            } else {
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }

            if (set.size() == map.size()) {
                if (right - left < distance) {
                    distance = right - left;
                    start = left + 1;
                    end = right;
                }
            }
        }

        answer[0] = start;
        answer[1] = end;

        return answer;
    }

    public static void main(String[] args) {
        String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };

        System.out.println(Arrays.toString(solution(gems)));
    }
}
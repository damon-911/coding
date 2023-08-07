package programmers.hash.가장가까운같은글자;

import java.util.*;

public class Solution {
    public static int[] solution(String s) {
        int[] answer = new int[s.length()];

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < answer.length; i++) {
            char c = s.charAt(i);

            if (map.containsKey(c))
                answer[i] = i - map.get(c);
            else
                answer[i] = -1;

            map.put(c, i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "banana";

        System.out.println(Arrays.toString(solution(s)));
    }
}
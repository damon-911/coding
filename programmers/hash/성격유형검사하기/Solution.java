package programmers.hash.성격유형검사하기;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    static int[] score = { 0, 3, 2, 1, 0, 1, 2, 3 };

    public static String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            String str = survey[i];
            int choice = choices[i];

            if (choice == 4)
                continue;
            else if (choice < 4)
                map.put(str.charAt(0), map.getOrDefault(str.charAt(0), 0) + score[choice]);
            else
                map.put(str.charAt(1), map.getOrDefault(str.charAt(1), 0) + score[choice]);
        }

        answer.append(map.getOrDefault('R', 0) >= map.getOrDefault('T', 0) ? 'R' : 'T');
        answer.append(map.getOrDefault('C', 0) >= map.getOrDefault('F', 0) ? 'C' : 'F');
        answer.append(map.getOrDefault('J', 0) >= map.getOrDefault('M', 0) ? 'J' : 'M');
        answer.append(map.getOrDefault('A', 0) >= map.getOrDefault('N', 0) ? 'A' : 'N');

        return answer.toString();
    }

    public static void main(String[] args) {
        String[] survey = { "AN", "CF", "MJ", "RT", "NA" };
        int[] choices = { 5, 3, 2, 7, 5 };

        System.out.println(solution(survey, choices));
    }
}
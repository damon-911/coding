package programmers.bruteforce_search.모의고사;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int[] solution(int[] answers) {
        int[] p1 = new int[] { 1, 2, 3, 4, 5 };
        int[] p2 = new int[] { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] p3 = new int[] { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == p1[i % 5])
                scores[0]++;

            if (answers[i] == p2[i % 8])
                scores[1]++;

            if (answers[i] == p3[i % 10])
                scores[2]++;
        }

        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));

        List<Integer> answerList = new ArrayList<Integer>();
        for (int i = 0; i < scores.length; i++) {
            if (max == scores[i])
                answerList.add(i + 1);
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answers = { 1, 2, 3, 4, 5 };

        System.out.println(Arrays.toString(solution(answers)));
    }
}
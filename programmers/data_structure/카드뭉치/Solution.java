package programmers.data_structure.카드뭉치;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> q1 = new LinkedList<>();
        for (String card : cards1)
            q1.offer(card);

        Queue<String> q2 = new LinkedList<>();
        for (String card : cards2)
            q2.offer(card);

        for (String word : goal) {
            String card1 = q1.peek();
            String card2 = q2.peek();

            if (word.equals(card1)) {
                q1.poll();
            } else if (word.equals(card2)) {
                q2.poll();
            } else
                return "No";
        }

        return "Yes";
    }

    public static void main(String[] args) {
        String[] cards1 = { "i", "drink", "water" };
        String[] cards2 = { "want", "to" };
        String[] goal = { "i", "want", "to", "drink", "water" };

        System.out.println(solution(cards1, cards2, goal));
    }
}
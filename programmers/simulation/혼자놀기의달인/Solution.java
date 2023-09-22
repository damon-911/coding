package programmers.simulation.혼자놀기의달인;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Solution {

    static boolean[] visited;
    static int count;

    static void open(int num, int[] cards) {
        if (!visited[num]) {
            visited[num] = true;
            count += 1;
            open(cards[num - 1], cards);
        }
    }

    public static int solution(int[] cards) {
        int answer = 0;

        visited = new boolean[cards.length + 1];

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i + 1]) {
                count = 1;
                visited[i + 1] = true;
                open(cards[i], cards);
                list.add(count);
            }
        }

        if (list.size() < 2) {
            return 0;
        }

        Collections.sort(list, Collections.reverseOrder());
        answer = list.get(0) * list.get(1);

        return answer;
    }

    public static void main(String[] args) {
        int[] cards = { 8, 6, 3, 7, 2, 5, 1, 4 };

        System.out.println(solution(cards));
    }
}
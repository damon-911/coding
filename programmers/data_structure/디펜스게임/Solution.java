package programmers.data_structure.디펜스게임;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public static int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            pq.add(enemy[i]);

            if (n < 0) {
                if (k > 0 && !pq.isEmpty()) {
                    n += pq.poll();
                    k--;
                } else {
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = { 4, 2, 3, 5, 3, 3, 1 };

        System.out.println(solution(n, k, enemy));
    }
}
package programmers.data_structure.야근지수;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public static long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pq.offer(work);
        }

        while (n > 0) {
            int temp = pq.poll();

            if (temp == 0)
                break;

            temp--;
            n--;
            pq.offer(temp);
        }

        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] works = { 4, 3, 3 };

        System.out.println(solution(n, works));
    }
}
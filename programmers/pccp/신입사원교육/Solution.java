package programmers.pccp.신입사원교육;

import java.util.PriorityQueue;

public class Solution {

    public static int solution(int[] ability, int number) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : ability) {
            pq.add(num);
        }

        for (int i = 0; i < number; i++) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + b);
            pq.add(a + b);
        }

        for (int i = 0; i < ability.length; i++) {
            answer += pq.poll();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ability = { 10, 3, 7, 2 };
        int number = 2;

        System.out.println(solution(ability, number));
    }
}
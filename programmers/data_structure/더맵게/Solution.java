package programmers.data_structure.더맵게;

import java.util.PriorityQueue;

public class Solution {

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : scoville) {
            pq.offer(num);
        }

        while (pq.peek() < K) {
            if (pq.size() == 1)
                return -1;

            int food1 = pq.poll();
            int food2 = pq.poll();

            pq.offer(food1 + food2 * 2);
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = { 1, 2, 3, 9, 10, 12 };
        int K = 7;

        System.out.println(solution(scoville, K));
    }
}
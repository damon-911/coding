package programmers.data_structure.프로세스;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : priorities) {
            pq.add(num);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                // 값이 일치할 경우
                if (priorities[i] == pq.peek()) {
                    pq.poll();
                    answer++;

                    // 인덱스도 동일할 경우
                    if (i == location)
                        return answer;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = { 2, 1, 3, 2 };
        int location = 2;

        System.out.println(solution(priorities, location));
    }
}
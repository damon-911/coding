package programmers.data_structure.두큐합같게만들기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long sum1 = 0;
        long sum2 = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int num : queue1) {
            sum1 += num;
            q1.offer(num);
        }

        for (int num : queue2) {
            sum2 += num;
            q2.offer(num);
        }

        if ((sum1 + sum2) % 2 == 1)
            return -1;

        while (true) {
            // q1에서 모든 수를 q2에 보내고
            // q2에서 하나만 제외하고 모두 q1으로 보내는 경우 = MAX
            if (answer >= queue1.length * 3)
                return -1;

            if (sum1 == sum2)
                break;
            else if (sum1 < sum2) {
                int num = q2.poll();
                q1.offer(num);
                sum1 += num;
                sum2 -= num;
            } else {
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] queue1 = { 3, 2, 7, 2 };
        int[] queue2 = { 4, 6, 5, 1 };

        System.out.println(solution(queue1, queue2));
    }
}
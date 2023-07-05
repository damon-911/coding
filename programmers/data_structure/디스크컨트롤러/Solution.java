package programmers.data_structure.디스크컨트롤러;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public static int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, (job1, job2) -> job1[0] - job2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int index = 0;
        int count = 0;
        int end = 0;

        while (count < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= end) {
                pq.add(jobs[index++]);
            }

            if (pq.isEmpty()) {
                end = jobs[index][0];
            } else {
                int[] cur = pq.poll();
                answer += cur[1] + (end - cur[0]);
                end += cur[1];
                count++;
            }
        }

        return answer / jobs.length;
    }

    public static void main(String[] args) {
        int[][] jobs = {
                { 0, 3 },
                { 1, 9 },
                { 2, 6 }
        };

        System.out.println(solution(jobs));
    }
}
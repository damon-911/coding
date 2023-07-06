package programmers.pccp.운영체제;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static long[] answer = new long[11];
    static PriorityQueue<int[]> waitPQ, sleepPQ;

    private static void run() {
        long time = -1;
        int run = 0;

        while (true) {
            if (waitPQ.isEmpty() && sleepPQ.isEmpty() && run == 0) {
                break;
            }

            time++;

            // 실행 중이라면 감소
            // 즉, 0이라면 실행 중 아님
            if (run > 0) {
                run--;
            }

            // 호출
            // 시간이 같다면, 슬립 힙에서 꺼내서 대기열로 넣습니다.
            while (!sleepPQ.isEmpty() && sleepPQ.peek()[1] == time) {
                waitPQ.add(sleepPQ.poll());
            }

            // 실행
            if (run == 0 && !waitPQ.isEmpty()) {
                int[] curProgram = waitPQ.poll();

                run += curProgram[2];

                answer[curProgram[0]] += time - curProgram[1];
            }
        }

        answer[0] = time;
    }

    public static long[] solution(int[][] program) {
        waitPQ = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        sleepPQ = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        for (int i = 0; i < program.length; i++) {
            sleepPQ.add(program[i]);
        }

        run();

        return answer;
    }

    public static void main(String[] args) {
        int[][] program = {
                { 2, 0, 10 },
                { 1, 5, 5 },
                { 3, 5, 3 },
                { 3, 12, 2 }
        };

        System.out.println(Arrays.toString(solution(program)));
    }
}
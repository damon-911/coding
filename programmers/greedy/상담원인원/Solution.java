package programmers.greedy.상담원인원;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static class Consult {
        int start;
        int time;

        public Consult(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }

    static int dfs(int remain, int[][] waitedSum, int depth, int k) {
        if (depth > k) {
            return 0;
        }

        int sum = Integer.MAX_VALUE;
        for (int i = 1; i <= remain; i++) {
            sum = Math.min(sum, dfs(remain - i + 1, waitedSum, depth + 1, k) + waitedSum[depth][i]);
        }

        return sum;
    }

    public static int solution(int k, int n, int[][] reqs) {
        List<List<Consult>> consultList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            consultList.add(new ArrayList<>());
        }

        for (int[] req : reqs) {
            consultList.get(req[2]).add(new Consult(req[0], req[1]));
        }

        int[][] waitedSum = new int[k + 1][n - k + 2]; // waitedSum[i][j] -> i = 상담 유형, j = 멘토 수
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n - k + 1; j++) {
                PriorityQueue<Integer> queue = new PriorityQueue<>();
                for (Consult consult : consultList.get(i)) {
                    if (queue.size() < j) {
                        queue.add(consult.start + consult.time);
                    } else {
                        int curTime = queue.poll();
                        int waitTime = curTime - consult.start;
                        if (waitTime > 0) {
                            waitedSum[i][j] += waitTime;
                            queue.add(curTime + consult.time);
                        } else {
                            queue.add(consult.start + consult.time);
                        }
                    }
                }
            }
        }

        return dfs(n - k + 1, waitedSum, 1, k);
    }

    public static void main(String[] args) {
        int k = 3;
        int n = 5;
        int[][] reqs = {
                { 10, 60, 1 },
                { 15, 100, 3 },
                { 20, 30, 1 },
                { 30, 50, 3 },
                { 50, 40, 1 },
                { 60, 30, 2 },
                { 65, 30, 1 },
                { 70, 100, 2 }
        };

        System.out.println(solution(k, n, reqs));
    }
}
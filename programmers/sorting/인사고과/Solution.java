package programmers.sorting.인사고과;

import java.util.Arrays;

public class Solution {

    public static int solution(int[][] scores) {
        int answer = 1;

        int[] wanho = scores[0];

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int peerPoint = 0;

        for (int[] score : scores) {
            if (score[1] < peerPoint) {
                if (wanho[0] == score[0] && wanho[1] == score[1])
                    return -1;
            } else {
                peerPoint = Math.max(score[1], peerPoint);
                if (wanho[0] + wanho[1] < score[0] + score[1])
                    answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] scores = {
                { 2, 2 },
                { 1, 4 },
                { 3, 2 },
                { 3, 2 },
                { 2, 1 }
        };

        System.out.println(solution(scores));
    }
}
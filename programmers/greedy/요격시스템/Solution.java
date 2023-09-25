package programmers.greedy.요격시스템;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] != t2[1] ? t1[1] - t2[1] : t1[0] - t2[0];
            }
        });

        int num = Integer.MIN_VALUE;

        for (int[] target : targets) {
            if (num <= target[0]) {
                num = target[1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] targets = {
                { 4, 5 },
                { 4, 8 },
                { 10, 14 },
                { 11, 13 },
                { 5, 12 },
                { 3, 7 },
                { 1, 4 }
        };

        System.out.println(solution(targets));
    }
}
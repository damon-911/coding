package programmers.array.n2배열자르기;

import java.util.Arrays;

public class Solution {

    public static int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];

        int idx = 0;

        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;
            answer[idx++] = Math.max((int) row, (int) col) + 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int left = 2;
        int right = 5;

        System.out.println(Arrays.toString(solution(n, left, right)));
    }
}
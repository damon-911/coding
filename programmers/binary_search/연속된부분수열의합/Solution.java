package programmers.binary_search.연속된부분수열의합;

import java.util.Arrays;

public class Solution {

    public static int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int minLength = Integer.MAX_VALUE;

        int start = 0;
        int end = 0;
        int sum = 0;

        while (end < sequence.length) {
            sum += sequence[end];

            while (sum > k) {
                sum -= sequence[start++];
            }

            if (sum == k) {
                if (minLength > end - start) {
                    minLength = end - start;
                    answer[0] = start;
                    answer[1] = end;
                }
            }

            end++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] sequence = { 1, 1, 1, 2, 3, 4, 5 };
        int k = 5;

        System.out.println(Arrays.toString(solution(sequence, k)));
    }
}
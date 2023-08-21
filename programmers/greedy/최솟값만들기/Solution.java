package programmers.greedy.최솟값만들기;

import java.util.Arrays;

public class Solution {

    public static int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer = answer + (A[i] * B[A.length - i - 1]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = { 1, 4, 2 };
        int[] B = { 5, 4, 4 };

        System.out.println(solution(A, B));
    }
}
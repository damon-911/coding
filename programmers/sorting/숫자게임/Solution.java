package programmers.sorting.숫자게임;

import java.util.Arrays;

public class Solution {

    public static int solution(int[] A, int[] B) {
        int answer = 0;

        int idx_A = 0;
        int idx_B = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        while (idx_B < B.length) {
            if (A[idx_A] < B[idx_B]) {
                answer++;
                idx_A++;
            }
            idx_B++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = { 5, 1, 3, 7 };
        int[] B = { 2, 2, 6, 8 };

        System.out.println(solution(A, B));
    }
}
package programmers.mathematics.줄서는방법;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {

    public static int[] solution(int n, long k) {
        int[] answer = new int[n];

        List<Integer> list = new ArrayList<>();

        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }

        k--;

        int idx = 0;
        while (idx < n) {
            factorial /= (n - idx);
            answer[idx++] = list.remove((int) (k / factorial));
            k %= factorial;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 5;

        System.out.println(Arrays.toString(solution(n, k)));
    }
}
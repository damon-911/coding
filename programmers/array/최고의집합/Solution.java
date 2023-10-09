package programmers.array.최고의집합;

import java.util.Arrays;

public class Solution {

    public static int[] solution(int n, int s) {
        if (n > s)
            return new int[] { -1 };

        int[] answer = new int[n];

        for (int i = 0; i < n; i++)
            answer[i] = s / n;

        int idx = n - 1;
        for (int i = 0; i < s % n; i++)
            answer[idx--]++;

        return answer;
    }

    public static void main(String[] args) {
        int n = 2;
        int s = 9;

        System.out.println(Arrays.toString(solution(n, s)));
    }
}
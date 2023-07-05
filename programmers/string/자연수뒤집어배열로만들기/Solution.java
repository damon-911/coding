package programmers.string.자연수뒤집어배열로만들기;

import java.util.*;

public class Solution {

    public static int[] solution(long n) {
        int[] answer = new int[String.valueOf(n).length()];

        long temp = n;
        int index = 0;

        while (temp > 0) {
            answer[index++] = (int) (temp % 10);
            temp = temp / 10;
        }

        return answer;
    }

    public static void main(String[] args) {
        long n = 12345;

        System.out.println(Arrays.toString(solution(n)));
    }
}
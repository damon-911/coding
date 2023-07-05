package programmers.string.이진변환반복하기;

import java.util.*;

public class Solution {

    public static String changeBinary(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(String.valueOf(n % 2));
            n /= 2;
        }

        return sb.reverse().toString();
    }

    public static int[] solution(String s) {
        int[] answer = new int[2];

        String str = s;

        while (str.length() > 1) {
            int count = 0;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0')
                    answer[1]++;
                else
                    count++;
            }

            str = changeBinary(count);
            answer[0]++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "110010101001";

        System.out.println(Arrays.toString(solution(s)));
    }
}
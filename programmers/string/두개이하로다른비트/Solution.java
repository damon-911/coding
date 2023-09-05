package programmers.string.두개이하로다른비트;

import java.util.Arrays;

public class Solution {

    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];

            if (num % 2 == 0) {
                answer[i] = num + 1;
            } else {
                String str = Long.toString(num, 2);
                int zeroIdx = str.lastIndexOf("0");

                if (zeroIdx != -1)
                    str = str.substring(0, zeroIdx) + "10" + str.substring(zeroIdx + 2, str.length());
                else
                    str = "10" + str.substring(1, str.length());

                answer[i] = Long.parseLong(str, 2);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        long[] numbers = { 2, 7 };

        System.out.println(Arrays.toString(solution(numbers)));
    }
}
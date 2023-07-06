package programmers.pccp.실습용로봇;

import java.util.Arrays;

public class Solution {

    static final int[] MX = { 0, 1, 0, -1 };
    static final int[] MY = { 1, 0, -1, 0 };

    public static int[] solution(String command) {
        int[] answer = new int[2];
        int flag = 0;

        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);

            if (c == 'R') {
                flag++;
                if (flag > 3)
                    flag = 0;
            } else if (c == 'L') {
                flag--;
                if (flag < 0)
                    flag = 3;
            } else if (c == 'G') {
                answer[0] += MX[flag];
                answer[1] += MY[flag];
            } else {
                answer[0] += MX[flag] * -1;
                answer[1] += MY[flag] * -1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String command = "GRGLGRG";

        System.out.println(Arrays.toString(solution(command)));
    }
}
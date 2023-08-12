package programmers.string.다트게임;

public class Solution {

    public static int solution(String dartResult) {
        int answer = 0;

        int[] sum = new int[3];

        String numstr = "";
        int idx = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            // 숫자일 때
            if (c >= '0' && c <= '9') {
                numstr += String.valueOf(c);
            }
            // 보너스일 때
            else if (c == 'S' || c == 'D' || c == 'T') {
                int num = Integer.parseInt(numstr);

                if (c == 'S') {
                    sum[idx++] = (int) Math.pow(num, 1);
                } else if (c == 'D') {
                    sum[idx++] = (int) Math.pow(num, 2);
                } else {
                    sum[idx++] = (int) Math.pow(num, 3);
                }

                numstr = "";
            }
            // 옵션일 때
            else {
                if (c == '*') {
                    sum[idx - 1] *= 2;
                    if (idx - 2 >= 0) {
                        sum[idx - 2] *= 2;
                    }
                } else {
                    sum[idx - 1] *= (-1);
                }
            }
        }

        for (int score : sum) {
            answer += score;
        }

        return answer;
    }

    public static void main(String[] args) {
        String dartResult = "1S2D*3T";

        System.out.println(solution(dartResult));
    }
}
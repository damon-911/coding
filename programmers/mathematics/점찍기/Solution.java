package programmers.mathematics.점찍기;

public class Solution {

    public static long solution(int k, int d) {
        long answer = 0;

        for (long i = 0; i <= d / k; i++) {
            long x = i * k;
            long y = (long) Math.sqrt((long) Math.pow(d, 2) - (long) Math.pow(x, 2)) / k;

            answer += y + 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 2;
        int d = 4;

        System.out.println(solution(k, d));
    }
}
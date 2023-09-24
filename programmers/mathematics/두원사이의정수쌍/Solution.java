package programmers.mathematics.두원사이의정수쌍;

public class Solution {

    public static long solution(int r1, int r2) {
        long answer = 0;

        for (int X = 1; X <= r2; X++) {
            long minY = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * X * X));
            long maxY = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * X * X));

            answer += (maxY - minY + 1);
        }

        return answer * 4;
    }

    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;

        System.out.println(solution(r1, r2));
    }
}
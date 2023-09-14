package programmers.mathematics.멀쩡한사각형;

public class Solution {

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static long solution(int w, int h) {
        long answer = 0;

        long gcdWH = gcd(w, h);

        answer = ((long) w * h) - ((w / gcdWH) + (h / gcdWH) - 1) * gcdWH;

        return answer;
    }

    public static void main(String[] args) {
        int W = 8;
        int H = 12;

        System.out.println(solution(W, H));
    }
}
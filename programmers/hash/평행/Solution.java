package programmers.hash.평행;

public class Solution {

    public static double calc(int a, int b, int[][] dots) {
        return (double) (dots[b][1] - dots[a][1]) / (dots[b][0] - dots[a][0]);
    }

    public static int solution(int[][] dots) {
        int answer = 0;

        if (calc(0, 1, dots) == calc(2, 3, dots))
            answer = 1;

        if (calc(0, 2, dots) == calc(1, 3, dots))
            answer = 1;

        if (calc(0, 3, dots) == calc(1, 2, dots))
            answer = 1;

        return answer;
    }

    public static void main(String[] args) {
        int[][] dots = {
                { 1, 4 },
                { 9, 2 },
                { 3, 8 },
                { 11, 6 }
        };

        System.out.println(solution(dots));
    }
}
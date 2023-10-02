package programmers.recursive.유사칸토어비트열;

public class Solution {

    static int countOne(int n, long s, long e, long idx) {
        if (n == 0) {
            return 1;
        }

        int num = 0;
        long part = (long) Math.pow(5, n - 1);

        for (int i = 0; i < 5; i++) {
            if (i == 2 || e < (idx + part * i) || (idx + part * (i + 1) - 1) < s)
                continue;
            num += countOne(n - 1, s, e, idx + part * i);
        }

        return num;
    }

    public static int solution(int n, long l, long r) {
        int answer = 0;

        answer = countOne(n, l, r, 1);

        return answer;
    }

    public static void main(String[] args) {
        int n = 2;
        int l = 4;
        int r = 17;

        System.out.println(solution(n, l, r));
    }
}
package programmers.mathematics.다음큰숫자;

public class Solution {

    public static int solution(int n) {
        int answer = 0;

        // String binaryNum = Integer.toBinaryString(n);
        int cnt = Integer.bitCount(n);

        for (int i = n + 1; i < 1000000; i++) {
            // String temp = Integer.toBinaryString(i);
            int tempCnt = Integer.bitCount(i);

            if (tempCnt == cnt) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 78;

        System.out.println(solution(n));
    }
}
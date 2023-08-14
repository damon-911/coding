package programmers.string.문자열나누기;

public class Solution {

    public static int solution(String s) {
        int answer = 0;

        char c = ' ';
        int same = 0;
        int diff = 0;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (c == ' ') {
                c = cur;
                same++;
                continue;
            }

            if (c == cur)
                same++;
            else
                diff++;

            if (same == diff) {
                c = ' ';
                same = 0;
                diff = 0;
                answer++;
            }
        }

        if (c == ' ')
            return answer;
        else
            return answer + 1;
    }

    public static void main(String[] args) {
        String s = "banana";

        System.out.println(solution(s));
    }
}
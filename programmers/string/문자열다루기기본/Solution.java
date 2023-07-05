package programmers.string.문자열다루기기본;

public class Solution {

    public static boolean solution(String s) {
        boolean answer = true;

        if (s.length() == 4 || s.length() == 6) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    answer = false;
                    break;
                }
            }
        } else
            answer = false;

        return answer;
    }

    public static void main(String[] args) {
        String s = "a234";

        System.out.println(solution(s));
    }
}
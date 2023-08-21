package programmers.string.JadenCase문자열만들기;

public class Solution {

    public static String solution(String s) {
        String answer = "";

        String[] strArr = s.split(" ");

        for (String str : strArr) {
            if (str.length() == 0) {
                answer += " ";
                continue;
            }

            answer += str.substring(0, 1).toUpperCase();
            answer += str.substring(1, str.length()).toLowerCase();
            answer += " ";
        }

        if (s.charAt(s.length() - 1) == ' ') {
            return answer;
        }

        return answer.substring(0, answer.length() - 1);
    }

    public static void main(String[] args) {
        String s = "3people unFollowed me";

        System.out.println(solution(s));
    }
}
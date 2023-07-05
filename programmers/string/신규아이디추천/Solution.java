package programmers.string.신규아이디추천;

public class Solution {

    public static String solution(String new_id) {
        String answer = new_id;

        // 1단계
        answer = answer.toLowerCase();

        // 2단계
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length(); i++) {
            char ch = answer.charAt(i);
            if (Character.isAlphabetic(ch) || Character.isDigit(ch)
                    || (ch == '-') || (ch == '_') || (ch == '.')) {
                sb.append(String.valueOf(ch));
            }
        }
        answer = sb.toString();

        // 3단계
        while (answer.indexOf("..") != -1) {
            answer = answer.replace("..", ".");
        }

        // 4단계
        if (!answer.isEmpty() && answer.charAt(0) == '.')
            answer = answer.substring(1);

        if (!answer.isEmpty() && answer.charAt(answer.length() - 1) == '.')
            answer = answer.substring(0, answer.length() - 1);

        // 5단계
        if (answer.isEmpty())
            answer = "a";

        // 6단계
        if (answer.length() > 15) {
            answer = answer.substring(0, 15);

            if (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, answer.length() - 1);
            }
        }

        // 7단계
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";

        System.out.println(solution(new_id));
    }
}
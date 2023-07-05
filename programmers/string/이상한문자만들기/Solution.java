package programmers.string.이상한문자만들기;

public class Solution {

    public static String solution(String s) {
        StringBuilder answer = new StringBuilder();

        // 문자열이 빈 문자열(ex. 연속된 공백 문자)로 끝나는 경우
        // 문자열.split(" ", -1)와 같이 호출하면 마지막에 오는 빈 문자열도 포함합니다.
        String[] words = s.split("", -1);

        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(" ")) {
                index = 0;
                answer.append(" ");
            } else if (index % 2 == 0) {
                index++;
                answer.append(words[i].toUpperCase());
            } else if (index % 2 == 1) {
                index++;
                answer.append(words[i].toLowerCase());
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String s = "try hello world";

        System.out.println(solution(s));
    }
}
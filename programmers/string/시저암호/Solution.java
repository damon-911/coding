package programmers.string.시저암호;

public class Solution {

    public static String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ')
                answer.append(' ');
            else if ((Character.isUpperCase(c) && c + n > 90)
                    || (Character.isLowerCase(c) && c + n > 122)) {
                answer.append((char) (c + n - 26));
            } else {
                answer.append((char) (c + n));
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String s = "AB";
        int n = 1;

        System.out.println(solution(s, n));
    }
}
package programmers.string.둘만의암호;

public class Solution {

    public static String solution(String s, String skip, int index) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            for (int j = 0; j < index; j++) {
                c += 1;

                if (c > 'z') {
                    c -= 26;
                }

                if (skip.contains(String.valueOf(c))) {
                    j--;
                }
            }

            answer += c;
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        System.out.println(solution(s, skip, index));
    }
}
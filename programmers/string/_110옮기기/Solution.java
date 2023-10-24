package programmers.string._110옮기기;

import java.util.Arrays;

public class Solution {

    static String makeMin(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder target = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (sb.length() >= 3
                    && sb.charAt(sb.length() - 3) == '1'
                    && sb.charAt(sb.length() - 2) == '1'
                    && sb.charAt(sb.length() - 1) == '0') {
                target.append("110");
                sb.delete(sb.length() - 3, sb.length());
            }
        }

        if (target.length() > 0) {
            if (sb.indexOf("0") == -1) { // 모든 수가 1로 이루어져 있는 경우
                sb.insert(0, target);
            } else {
                sb.insert(sb.lastIndexOf("0") + 1, target);
            }
        }

        return sb.toString();
    }

    public static String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            answer[i] = makeMin(s[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] s = { "1110", "100111100", "0111111010" };

        System.out.println(Arrays.toString(solution(s)));
    }
}
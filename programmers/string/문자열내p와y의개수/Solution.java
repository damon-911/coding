package programmers.string.문자열내p와y의개수;

public class Solution {

    public static boolean solution(String s) {
        boolean answer = true;

        int num_p = 0;
        int num_y = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p' || s.charAt(i) == 'P')
                num_p++;
            else if (s.charAt(i) == 'y' || s.charAt(i) == 'Y')
                num_y++;
        }

        if (num_p != num_y)
            answer = false;

        return answer;
    }

    public static void main(String[] args) {
        String s = "pPoooyY";

        System.out.println(solution(s));
    }
}
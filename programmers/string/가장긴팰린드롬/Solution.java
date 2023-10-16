package programmers.string.가장긴팰린드롬;

public class Solution {

    static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static int solution(String s) {
        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j + i <= s.length(); j++) {
                if (isPalindrome(s, j, j + i - 1)) {
                    return i;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "abcdcba";

        System.out.println(solution(s));
    }
}
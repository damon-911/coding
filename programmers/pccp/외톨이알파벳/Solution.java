package programmers.pccp.외톨이알파벳;

import java.util.Set;
import java.util.TreeSet;

public class Solution {

    public static String solution(String input_string) {
        String answer = "";

        boolean[] visited = new boolean[26];

        Set<Character> set = new TreeSet<>();

        char[] cArr = input_string.toCharArray();
        char cur = ' ';

        for (char c : cArr) {
            if (c != cur) {
                if (visited[c - 'a']) {
                    set.add(c);
                }

                cur = c;
                visited[c - 'a'] = true;
            }
        }

        for (char temp : set) {
            answer += temp;
        }

        if (answer.length() == 0)
            answer += "N";

        return answer;
    }

    public static void main(String[] args) {
        String input_string = "edeaaabbccd";

        System.out.println(solution(input_string));
    }
}
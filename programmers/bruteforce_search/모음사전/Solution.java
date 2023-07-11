package programmers.bruteforce_search.모음사전;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static List<String> list = new ArrayList<>();

    private static void dfs(String str, int len) {
        if (len > 5)
            return;

        list.add(str);

        for (int i = 0; i < 5; i++)
            dfs(str + "AEIOU".charAt(i), len + 1);
    }

    public static int solution(String word) {
        dfs("", 0);

        return list.indexOf(word);
    }

    /*
     * public static int solution(String word) {
     * int answer = 0;
     * 
     * String str = "AEIOU";
     * int[] count = { 781, 156, 31, 6, 1 };
     * 
     * for (int i = 0; i < word.length(); i++) {
     * int index = str.indexOf(word.charAt(i));
     * answer += count[i] * index + 1;
     * }
     * 
     * return answer;
     * }
     */

    public static void main(String[] args) {
        String word = "AAAAE";

        System.out.println(solution(word));
    }
}
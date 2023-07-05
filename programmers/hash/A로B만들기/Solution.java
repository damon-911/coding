package programmers.hash.A로B만들기;

import java.util.Arrays;

public class Solution {

    public static int solution(String before, String after) {
        int answer = 0;

        char[] beforeArr = before.toCharArray();
        char[] afterArr = after.toCharArray();

        Arrays.sort(beforeArr);
        Arrays.sort(afterArr);

        String beforeStr = new String(beforeArr);
        String afterStr = new String(afterArr);

        if (beforeStr.equals(afterStr))
            answer = 1;
        else
            answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        String before = "olleh";
        String after = "hello";

        System.out.println(solution(before, after));
    }
}
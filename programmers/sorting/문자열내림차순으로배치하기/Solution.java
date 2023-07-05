package programmers.sorting.문자열내림차순으로배치하기;

import java.util.Arrays;

public class Solution {

    public static String solution(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder(String.valueOf(arr));
        sb.reverse();

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Zbcdefg";

        System.out.println(solution(s));
    }
}
package programmers.sorting.문자열내마음대로정렬하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    public static String[] solution(String[] strings, int n) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add("" + strings[i].charAt(n) + strings[i]);
        }
        Collections.sort(list);

        String[] answer = new String[strings.length];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).substring(1, list.get(i).length());
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] strings = { "sun", "bed", "car" };
        int n = 1;

        System.out.println(Arrays.toString(solution(strings, n)));
    }
}
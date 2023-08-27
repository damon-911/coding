package programmers.string.뉴스클러스터링;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int solution(String str1, String str2) {
        double answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> union = new ArrayList<>();
        List<String> intersection = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            char first = str1.charAt(i);
            char second = str1.charAt(i + 1);

            if (first >= 'a' && first <= 'z' && second >= 'a' && second <= 'z') {
                list1.add(first + "" + second);
            }
        }

        for (int i = 0; i < str2.length() - 1; ++i) {
            char first = str2.charAt(i);
            char second = str2.charAt(i + 1);

            if (first >= 'a' && first <= 'z' && second >= 'a' && second <= 'z') {
                list2.add(first + "" + second);
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        for (String s : list1) {
            if (list2.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }

        for (String s : list2) {
            union.add(s);
        }

        if (union.size() == 0) {
            answer = 1;
        } else {
            answer = (double) intersection.size() / (double) union.size();
        }

        return (int) (answer * 65536);
    }

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";

        System.out.println(solution(str1, str2));
    }
}
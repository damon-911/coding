package programmers.sorting.파일명정렬;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    static String[] detach(String str) {
        String head = "";
        String number = "";
        String tail = "";

        int idx = 0;

        for (; idx < str.length(); idx++) {
            char ch = str.charAt(idx);
            if (ch >= '0' && ch <= '9')
                break;
            head += ch;
        }

        for (; idx < str.length(); idx++) {
            char ch = str.charAt(idx);
            if (!(ch >= '0' && ch <= '9'))
                break;
            number += ch;
        }

        for (; idx < str.length(); idx++) {
            char ch = str.charAt(idx);
            tail += ch;
        }

        String[] file = { head.toLowerCase(), number, tail };

        return file;
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] file1 = detach(s1);
                String[] file2 = detach(s2);

                int diff = file1[0].compareTo(file2[0]);

                return diff != 0 ? diff : Integer.parseInt(file1[1]) - Integer.parseInt(file2[1]);
            }
        });

        return files;
    }

    public static void main(String[] args) {
        String[] files = { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };

        System.out.println(Arrays.toString(solution(files)));
    }
}
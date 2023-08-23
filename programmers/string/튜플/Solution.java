package programmers.string.튜플;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {

    public static int[] solution(String s) {
        HashSet<Integer> hashSet = new HashSet<>();

        String[] arr = s.replaceAll("[\\{\\}]", " ").trim().split(" ,");
        Arrays.sort(arr, (a, b) -> (a.length() - b.length()));

        int[] answer = new int[arr.length];
        int idx = 0;

        for (String strs : arr) {
            for (String str : strs.split(",")) {
                int num = Integer.parseInt(str.trim());

                if (hashSet.contains(num))
                    continue;

                hashSet.add(num);
                answer[idx++] = num;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";

        System.out.println(Arrays.toString(solution(s)));
    }
}
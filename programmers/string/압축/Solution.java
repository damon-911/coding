package programmers.string.압축;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        int start = 0;

        Map<String, Integer> dic = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dic.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        while (start < msg.length()) {
            int end = msg.length();
            String temp = "";

            while (true) {
                temp = msg.substring(start, end);

                if (dic.containsKey(temp)) {
                    result.add(dic.get(temp));
                    break;
                } else {
                    end--;
                }
            }

            if (end != msg.length())
                dic.put(msg.substring(start, end + 1), dic.size() + 1);

            start = end;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String msg = "KAKAO";

        System.out.println(Arrays.toString(solution(msg)));
    }
}
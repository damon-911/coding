package programmers.sorting.메뉴리뉴얼;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    static List<String> answerList = new ArrayList<>();
    static Map<String, Integer> hashMap = new HashMap<>();

    public static void comb(String output, String order, int count) {
        if (count == 0) {
            hashMap.put(output, hashMap.getOrDefault(output, 0) + 1);
            return;
        }

        for (int i = 0; i < order.length(); i++) {
            comb(output + order.charAt(i), order.substring(i + 1), count - 1);
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        // 1. 각 Order 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // 2. 각 order를 기준으로 courseLength 만큼의 조합 만들기
        for (int courseLength : course) {
            for (String order : orders)
                comb("", order, courseLength);

            // 3. 가장 많은 조합 answer에 저장
            if (!hashMap.isEmpty()) {
                List<Integer> countList = new ArrayList<>(hashMap.values());
                int max = Collections.max(countList);

                if (max > 1) {
                    for (String key : hashMap.keySet()) {
                        if (hashMap.get(key) == max) {
                            answerList.add(key);
                        }
                    }
                }
                hashMap.clear();
            }
        }

        Collections.sort(answerList);

        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
        int[] course = { 2, 3, 4 };

        System.out.println(Arrays.toString(solution(orders, course)));
    }
}
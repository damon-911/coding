package programmers.hash.할인행사;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        Map<String, Integer> wMap = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            wMap.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> dMap = new HashMap<>();

            for (int j = i; j < i + 10; j++) {
                dMap.put(discount[j], dMap.getOrDefault(discount[j], 0) + 1);
            }

            boolean isSame = true;

            for (String key : wMap.keySet()) {
                if (wMap.get(key) != dMap.get(key)) {
                    isSame = false;
                    break;
                }
            }

            answer += isSame ? 1 : 0;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] want = { "banana", "apple", "rice", "pork", "pot" };
        int[] number = { 3, 2, 2, 2, 1 };
        String[] discount = { "chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice",
                "pot", "banana", "apple", "banana" };

        System.out.println(solution(want, number, discount));
    }
}
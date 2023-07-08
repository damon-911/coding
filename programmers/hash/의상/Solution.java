package programmers.hash.의상;

import java.util.HashMap;

public class Solution {

    public static int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            hashMap.put(clothes[i][1], hashMap.getOrDefault(clothes[i][1], 0) + 1);
        }

        for (String key : hashMap.keySet()) {
            answer *= hashMap.get(key) + 1;
        }

        // 의상을 하나도 착용하지 않은 경우가 포함되어 있으므로 하나 빼야됨
        answer -= 1;

        return answer;
    }

    public static void main(String[] args) {
        String[][] clothes = {
                { "yellow_hat", "headgear" },
                { "blue_sunglasses", "eyewear" },
                { "green_turban", "headgear" }
        };

        System.out.println(solution(clothes));
    }
}
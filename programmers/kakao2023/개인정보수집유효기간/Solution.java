package programmers.kakao2023.개인정보수집유효기간;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    private static int getDate(String today) {
        String[] date = today.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();

        int todayDate = getDate(today);

        HashMap<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] temp = term.split(" ");
            termMap.put(temp[0], Integer.parseInt(temp[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            if (getDate(privacy[0]) + (termMap.get(privacy[1]) * 28) <= todayDate) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer -> Integer).toArray();
    }

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = { "A 6", "B 12", "C 3" };
        String[] privacies = { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" };

        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }
}
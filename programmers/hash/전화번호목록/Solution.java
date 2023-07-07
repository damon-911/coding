package programmers.hash.전화번호목록;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Set<String> set = new HashSet<>();

        for (String phone : phone_book) {
            set.add(phone);
        }

        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                if (set.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] phone_book = { "119", "97674223", "1195524421" };

        System.out.println(solution(phone_book));
    }
}
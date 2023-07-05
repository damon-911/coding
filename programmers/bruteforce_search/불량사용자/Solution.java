package programmers.bruteforce_search.불량사용자;

import java.util.HashSet;

public class Solution {

    static String[] userIds;
    static String[] bannedIds;
    static HashSet<HashSet<String>> result = new HashSet<>();

    public static boolean check(String userId, String bannedId) {
        if (userId.length() != bannedId.length())
            return false;

        boolean match = true;
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && (userId.charAt(i) != bannedId.charAt(i))) {
                match = false;
                break;
            }
        }

        return match;
    }

    public static void comb(HashSet<String> set, int depth) {
        if (depth == bannedIds.length) {
            result.add(set);
            return;
        }

        for (int i = 0; i < userIds.length; i++) {
            if (set.contains(userIds[i])) {
                continue;
            }

            if (check(userIds[i], bannedIds[depth])) {
                set.add(userIds[i]);
                comb(new HashSet<>(set), depth + 1);
                set.remove(userIds[i]);
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;

        comb(new HashSet<>(), 0);

        return result.size();
    }

    public static void main(String[] args) {
        String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
        String[] banned_id = { "fr*d*", "abc1**" };

        System.out.println(solution(user_id, banned_id));
    }
}
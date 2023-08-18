package programmers.hash.달리기경주;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];

        Map<String, Integer> playerMap = new HashMap<>();
        Map<Integer, String> rankMap = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            playerMap.put(players[i], i);
            rankMap.put(i, players[i]);
        }

        for (int i = 0; i < callings.length; i++) {
            int curRank = playerMap.get(callings[i]);
            String curPlayer = rankMap.get(curRank);

            // 바로 앞 플레이어
            String frontPlayer = rankMap.get(curRank - 1);

            // swap
            playerMap.put(curPlayer, curRank - 1);
            playerMap.put(frontPlayer, curRank);

            rankMap.put(curRank - 1, curPlayer);
            rankMap.put(curRank, frontPlayer);
        }

        for (int i = 0; i < players.length; i++) {
            answer[i] = rankMap.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] players = { "mumu", "soe", "poe", "kai", "mine" };
        String[] callings = { "kai", "kai", "mine", "mine" };

        System.out.println(Arrays.toString(solution(players, callings)));
    }
}
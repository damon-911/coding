package programmers.kakao2022.신고결과받기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, HashSet<String>> reporterMap = new HashMap<>();
        Map<String, Integer> idxMap = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            reporterMap.put(id, new HashSet<>());
            idxMap.put(id, i);
        }

        for (String reportInfo : report) {
            String reporter = reportInfo.split(" ")[0]; // 신고 한 사람
            String reported = reportInfo.split(" ")[1]; // 신고 당한 사람

            reporterMap.get(reported).add(reporter);
        }

        for (String id : id_list) {
            HashSet<String> send = reporterMap.get(id);
            if (send.size() >= k) {
                for (String name : send) {
                    answer[idxMap.get(name)]++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = { "muzi", "frodo", "apeach", "neo" };
        String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
        int k = 2;

        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }
}
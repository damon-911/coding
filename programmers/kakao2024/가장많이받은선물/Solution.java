package programmers.kakao2024.가장많이받은선물;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 친구들 이름과 인덱스 저장
        Map<String, Integer> friendMap = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendMap.put(friends[i], i);
        }

        // 선물을 주고받은 기록 저장
        int[][] giftGraph = new int[friends.length][friends.length];
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            giftGraph[friendMap.get(temp[0])][friendMap.get(temp[1])]++;
        }

        // 0 : 준 선물, 1 : 받은 선물, 2 : 선물 지수, 3 : 다음 달에 받을 선물
        int[][] result = new int[friends.length][4];

        // 각 친구들 별 result 구하기
        for (Map.Entry<String, Integer> mapEntry : friendMap.entrySet()) {
            int idx = mapEntry.getValue();

            // 선물 준 횟수
            int givenCnt = 0;
            for (int i = 0; i < friends.length; i++) {
                givenCnt += giftGraph[idx][i];
            }
            result[idx][0] = givenCnt;

            // 선물 받은 횟수
            int receivedCnt = 0;
            for (int i = 0; i < friends.length; i++) {
                receivedCnt += giftGraph[i][idx];
            }
            result[idx][1] = receivedCnt;

            // 선물 지수 = 선물 준 횟수 - 선물 받은 횟수
            result[idx][2] = givenCnt - receivedCnt;
        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                // i가 준 선물이 더 많을 경우
                if (giftGraph[i][j] > giftGraph[j][i]) {
                    result[i][3]++;
                }
                // j가 준 선물이 더 많은 경우
                else if (giftGraph[i][j] < giftGraph[j][i]) {
                    result[j][3]++;
                }
                // i가 준 선물 수와 j가 준 선물 수가 같은 경우
                else {
                    // 선물 지수가 i가 큰 경우
                    if (result[i][2] > result[j][2]) {
                        result[i][3]++;
                    }
                    // 선물 지수가 j가 큰 경우
                    else if (result[i][2] < result[j][2]) {
                        result[j][3]++;
                    }
                    // 선물 지수가 같은 경우
                    else {
                        continue;
                    }
                }
            }
        }

        for (int i = 0; i < friends.length; i++) {
            answer = Math.max(answer, result[i][3]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] friends = { "muzi", "ryan", "frodo", "neo" };
        String[] gifts = {
                "muzi frodo",
                "muzi frodo",
                "ryan muzi",
                "ryan muzi",
                "ryan muzi",
                "frodo muzi",
                "frodo ryan",
                "neo muzi"
        };

        System.out.println(solution(friends, gifts));
    }
}
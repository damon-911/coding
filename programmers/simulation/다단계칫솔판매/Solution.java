package programmers.simulation.다단계칫솔판매;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, String> parentMap = new HashMap<>(); // <자신, 부모>
        Map<String, Integer> indexMap = new HashMap<>(); // <자신, 자신의 인덱스>

        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {
            String me = seller[i];
            int profit = 100 * amount[i];

            while (!me.equals("-")) {
                int myProfit = profit - profit / 10;
                answer[indexMap.get(me)] += myProfit;

                me = parentMap.get(me);
                profit /= 10;

                if (profit < 1) {
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
        String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
        String[] seller = { "young", "john", "tod", "emily", "mary" };
        int[] amount = { 12, 4, 2, 5, 10 };

        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }
}
package programmers.kakao2024.n_1카드게임;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    static Set<Integer> hand = new HashSet<>();
    static Set<Integer> pick = new HashSet<>();

    static int solution(int coin, int[] cards) {
        int answer = 0;

        int cardNum = cards.length;
        int idx = cardNum / 3;

        for (int i = 0; i < idx; ++i) {
            hand.add(cards[i]);
        }

        int target = cardNum + 1;

        while (true) {
            answer++;

            if (idx >= cardNum) {
                break;
            }

            pick.add(cards[idx++]);
            pick.add(cards[idx++]);

            boolean flag = false;

            // 1. 손에 있는 카드로 해결 가능한지 확인
            for (int i : hand) {
                if (hand.contains(target - i)) {
                    hand.remove(i);
                    hand.remove(target - i);
                    flag = true;
                    break;
                }
            }

            // 2. 해결되지 않았다면 추가 카드 1장을 이용해서 해결 가능한지 확인
            if (!flag) {
                // 최소 1개 이상의 코인이 있어야 추가 카드를 받아서 해결 가능
                if (coin > 0) {
                    for (int i : hand) {
                        if (pick.contains(target - i)) {
                            hand.remove(i);
                            pick.remove(target - i);
                            coin--;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            // 3. 그래도 해결되지 않았다면 추가 카드들로만 해결 가능한지 확인
            if (!flag) {
                // 최소 2개 이상의 코인이 있어야 추가 카드를 중에서 해결 가능
                if (coin > 1) {
                    for (int i : pick) {
                        if (pick.contains(target - i)) {
                            pick.remove(i);
                            pick.remove(target - i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            // 4. 라운드 종료
            if (!flag)
                break;
        }

        return answer;
    }

    public static void main(String[] args) {
        int coin = 4;
        int[] cards = { 3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4 };

        System.out.println(solution(coin, cards));
    }
}
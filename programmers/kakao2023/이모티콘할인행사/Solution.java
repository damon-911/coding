package programmers.kakao2023.이모티콘할인행사;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static final double[] discount = { 0.1, 0.2, 0.3, 0.4 };

    static List<Imoticon> imList = new ArrayList<>();
    static int maxJoin = Integer.MIN_VALUE;
    static int maxTotal = Integer.MIN_VALUE;

    static class Imoticon {
        double percent;
        double price;

        Imoticon(double percent, double price) {
            this.percent = percent;
            this.price = price;
        }
    }

    private static void dfs(int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            int join = 0;
            int total = 0;

            for (int i = 0; i < users.length; i++) {
                int userPercent = users[i][0];
                int userPrice = users[i][1];

                // 개인이 이모티콘을 구매한 금액의 합
                int sum = 0;
                for (int j = 0; j < imList.size(); j++) {
                    Imoticon cur = imList.get(j);
                    double curPercent = cur.percent;
                    double curPrice = cur.price;

                    if (curPercent >= userPercent)
                        sum += curPrice;
                }

                if (sum >= userPrice)
                    join++;
                else
                    total += sum;

                if (maxJoin < join) {
                    maxJoin = join;
                    maxTotal = total;
                } else if (maxJoin == join && maxTotal < total) {
                    maxTotal = total;
                }
            }
            return;
        }

        for (int i = 0; i < discount.length; i++) {
            imList.add(new Imoticon(discount[i] * 100, ((1 - discount[i]) * emoticons[depth])));
            dfs(depth + 1, users, emoticons);
            imList.remove(imList.size() - 1);
        }
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        // 중복 순열 수행
        dfs(0, users, emoticons);

        answer[0] = maxJoin;
        answer[1] = maxTotal;

        return answer;
    }

    public static void main(String[] args) {
        int[][] users = {
                { 40, 10000 },
                { 25, 10000 }
        };
        int[] emoticons = { 7000, 9000 };

        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
}
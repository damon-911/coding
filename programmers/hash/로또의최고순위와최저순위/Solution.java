package programmers.hash.로또의최고순위와최저순위;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    static int getRank(int count) {
        switch (count) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        Set<Integer> winNumSet = new HashSet<>();
        for (int num : win_nums)
            winNumSet.add(num);

        int count = 0;
        int zero = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zero++;
                continue;
            }
            if (winNumSet.contains(lotto))
                count++;
        }

        answer[0] = getRank(count + zero);
        answer[1] = getRank(count);

        return answer;
    }

    public static void main(String[] args) {
        int[] lottos = { 44, 1, 0, 0, 31, 25 };
        int[] win_nums = { 31, 10, 45, 1, 6, 19 };

        System.out.println(Arrays.toString(solution(lottos, win_nums)));
    }
}
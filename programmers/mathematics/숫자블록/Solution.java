package programmers.mathematics.숫자블록;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {

    static final int BLOCK_LIMIT = 10000000;

    static int getMax(int num) {
        if (num == 1) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                list.add(i);
                if (num / i <= BLOCK_LIMIT) {
                    return num / i;
                }
            }
        }

        if (!list.isEmpty()) {
            return list.get(list.size() - 1);
        }

        return 1;
    }

    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];

        int idx = 0;
        for (int cur = (int) begin; cur <= end; cur++) {
            answer[idx++] = getMax(cur);
        }

        return answer;
    }

    public static void main(String[] args) {
        int begin = 1;
        int end = 10;

        System.out.println(Arrays.toString(solution(begin, end)));
    }
}
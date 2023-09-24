package programmers.mathematics.우박수열정적분;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {

    public static double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Integer> list = new ArrayList<>();

        while (k > 1) {
            list.add(k);

            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
        }
        list.add(k);

        int count = list.size() - 1;

        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i][0] > ranges[i][1] + count) {
                answer[i] = -1;
                continue;
            } else if (ranges[i][0] == ranges[i][1] + count) {
                answer[i] = 0;
                continue;
            }

            double dimension = 0;

            for (int j = ranges[i][0]; j < ranges[i][1] + count; j++) {
                dimension += (list.get(j) + list.get(j + 1)) / 2.0;
            }

            answer[i] = dimension;
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {
                { 0, 0 },
                { 0, -1 },
                { 2, -3 },
                { 3, -3 }
        };

        System.out.println(Arrays.toString(solution(k, ranges)));
    }
}
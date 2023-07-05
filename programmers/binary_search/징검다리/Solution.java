package programmers.binary_search.징검다리;

import java.util.Arrays;

public class Solution {

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            int prev = 0;

            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid)
                    cnt++;
                else
                    prev = rocks[i];
            }

            if (distance - prev < mid)
                cnt++;

            if (cnt <= n) {
                answer = mid;
                left = mid + 1;
            } else
                right = mid - 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = { 2, 14, 11, 21, 17 };
        int n = 2;

        System.out.println(solution(distance, rocks, n));
    }
}
package programmers.binary_search.입국심사;

import java.util.Arrays;

public class Solution {
    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long left = 0;
        long right = (long) n * times[times.length - 1];

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;

            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            if (sum < n) { // 해야할 인원보다 심사하지 않음
                left = mid + 1;
            } else { // 해야할 인원보다 더 많이 심사함
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = { 7, 10 };

        System.out.println(solution(n, times));
    }
}
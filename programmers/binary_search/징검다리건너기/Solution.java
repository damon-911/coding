package programmers.binary_search.징검다리건너기;

public class Solution {

    private static boolean canAcross(int[] stones, int k, int num) {
        // 못 건너는 징검다리 수
        int cnt = 0;

        // num 이하의 수가 연속되는 숫자가 k번 이상인지 체크
        for (int stone : stones) {
            if (stone - num < 0)
                cnt++;
            else
                cnt = 0;

            if (cnt == k)
                return false;
        }

        return true;
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canAcross(stones, k, mid)) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
        int k = 3;

        System.out.println(solution(stones, k));
    }
}
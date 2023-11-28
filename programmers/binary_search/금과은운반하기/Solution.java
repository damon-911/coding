package programmers.binary_search.금과은운반하기;

public class Solution {

    static boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long total = 0L;
        long totalG = 0L;
        long totalS = 0L;

        for (int i = 0; i < g.length; i++) {
            // 해당 시간에 옮길 수 있는 횟수
            long cnt = time / (2L * t[i]);
            if (time % (2L * t[i]) >= t[i])
                cnt++;

            // 해당 시간에 옮길 수 있는 무게
            long temp = Math.min(cnt * w[i], g[i] + s[i]);

            total += temp;
            totalG += Math.min(temp, g[i]);
            totalS += Math.min(temp, s[i]);
        }

        if (total >= a + b && totalG >= a && totalS >= b)
            return true;

        return false;
    }

    public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long low = 0;
        long high = (long) ((10e9 * 2) * (10e5 * 2));

        // 이분 탐색
        while (low < high) {
            long mid = (long) ((low + high) / 2);

            if (isPossible(mid, a, b, g, s, w, t)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        int a = 90;
        int b = 500;
        int[] g = { 70, 70, 0 };
        int[] s = { 0, 0, 500 };
        int[] w = { 100, 100, 2 };
        int[] t = { 4, 8, 1 };

        System.out.println(solution(a, b, g, s, w, t));
    }
}

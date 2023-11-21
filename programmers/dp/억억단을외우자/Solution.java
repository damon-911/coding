package programmers.dp.억억단을외우자;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    static Point[] dp;

    static class Point {
        int num;
        int cnt;

        public Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void findCount(int e) {
        for (int i = 1; i <= e; i++) {
            dp[i - 1] = new Point(i, 1);
        }

        // 각 수의 약수의 갯수 모두 구하기
        for (int i = 2; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                dp[i * j - 1].cnt++;
            }
        }

        // cnt 역순, num 정순으로 정렬
        Arrays.sort(dp, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.cnt != p2.cnt) {
                    return p2.cnt - p1.cnt;
                } else {
                    return p1.num - p2.num;
                }
            }
        });
    }

    public static int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        dp = new Point[e];

        findCount(e);

        for (int i = 0; i < starts.length; i++) {
            int start = starts[i];
            for (int j = 0; j < e; j++) {
                if (dp[j].num >= start) {
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int e = 8;
        int[] starts = { 1, 3, 7 };

        System.out.println(Arrays.toString(solution(e, starts)));
    }
}
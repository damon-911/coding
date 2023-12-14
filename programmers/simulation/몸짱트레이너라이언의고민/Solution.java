package programmers.simulation.몸짱트레이너라이언의고민;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(int n, int m, int[][] timetable) {
        int answer = 0;

        // 600 ~ 1320
        int[] overlap = new int[722];

        // 1. 가장 많이 겹치는 회원 수 구하기
        for (int i = 0; i < m; i++) {
            overlap[timetable[i][0] - 600]++;
            overlap[timetable[i][1] - 600 + 1]--;
        }

        int sum = 0;
        int max = 0; // 가장 많이 겹치는 회원 수

        for (int i = 0; i <= 720; i++) {
            sum += overlap[i];
            overlap[i] = sum;
            max = Math.max(max, overlap[i]);
        }

        if (max <= 1)
            return 0;

        // 2. 가능한 거리 순회하며 max만큼 배치할 수 있으면 리턴
        for (int dis = 2 * (n - 1); dis >= 1; dis--) {
            // 첫번쨰 row의 시작지점을 변경
            for (int start = 0; start < n; start++) {
                List<Point> list = new ArrayList<>();
                // 맵 전체 순회
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == 0 && j < start)
                            continue;

                        // 해당 위치에 회원 들어갈 수 있는지 확인
                        boolean flag = true;
                        for (Point p : list) {
                            if (Math.abs(p.x - i) + Math.abs(p.y - j) >= dis)
                                continue;
                            flag = false;
                            break;
                        }

                        if (flag) {
                            list.add(new Point(i, j));
                            if (list.size() == max)
                                return dis;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 2;
        int[][] timetable = { { 1170, 1210 }, { 1200, 1260 } };

        System.out.println(solution(n, m, timetable));
    }
}
package programmers.graph.방의개수;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Solution {

    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
        }
    }

    private static List<Point> makeEdgeList(Point point) {
        List<Point> list = new ArrayList<>();
        list.add(point);
        return list;
    }

    public static int solution(int[] arrows) {
        int answer = 0;

        Map<Point, List<Point>> visited = new HashMap<>();
        Point now = new Point(0, 0);

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) { // 교차점 처리를 위한 스케일업
                Point next = new Point(now.x + dx[arrow], now.y + dy[arrow]);

                // 처음 방문하는 경우
                if (!visited.containsKey(next)) {
                    // 리스트에 연결점 추가
                    visited.put(next, makeEdgeList(now));

                    if (visited.get(now) == null) {
                        visited.put(now, makeEdgeList(next));
                    } else {
                        visited.get(now).add(next);
                    }
                }
                // 해당 정점을 재방문했고, 간선을 처음 통과하는 경우
                else if (visited.containsKey(next) && !visited.get(next).contains(now)) {
                    visited.get(next).add(now);
                    visited.get(now).add(next);

                    answer++;
                }

                now = next;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arrows = { 6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0 };

        System.out.println(solution(arrows));
    }
}
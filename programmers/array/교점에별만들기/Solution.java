package programmers.array.교점에별만들기;

import java.util.*;

public class Solution {

    public static class Point implements Comparable<Point> {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (p.y == this.y)
                return (int) (this.x - p.x);

            return (int) (p.y - this.y);
        }
    }

    static List<Point> crossPointList;

    static long maxX, maxY, minX, minY;

    public static boolean isInteger(double num) {
        return num == (long) num;
    }

    public static void findCrossPoint(int[][] lines) {
        // Ax + By = E, Cx + Dy = F
        // x = (BF - ED) / (AD - BC)
        // y = (EC - AF) / (AD - BC)
        for (int i = 0; i < lines.length - 1; i++) {
            long A = lines[i][0];
            long B = lines[i][1];
            long E = lines[i][2];

            for (int j = i + 1; j < lines.length; j++) {
                long C = lines[j][0];
                long D = lines[j][1];
                long F = lines[j][2];

                double x, y;

                long det = A * D - B * C;
                if (det == 0)
                    continue;

                x = (double) (B * F - E * D) / det;
                y = (double) (E * C - A * F) / det;

                if (!isInteger(x) || !isInteger(y))
                    continue;

                maxX = Math.max(maxX, (long) x);
                maxY = Math.max(maxY, (long) y);
                minX = Math.min(minX, (long) x);
                minY = Math.min(minY, (long) y);

                crossPointList.add(new Point((long) x, (long) y));
            }
        }
    }

    public static String[] solution(int[][] line) {
        minY = Long.MAX_VALUE;
        minX = Long.MAX_VALUE;
        maxY = Long.MIN_VALUE;
        maxX = Long.MIN_VALUE;

        crossPointList = new ArrayList<>();

        findCrossPoint(line);

        Collections.sort(crossPointList);

        String[] answer = new String[(int) (maxY - minY + 1)];
        String str = ".";
        StringBuilder sb = new StringBuilder(str.repeat((int) (maxX - minX + 1)));
        Arrays.fill(answer, sb.toString());

        long curY = crossPointList.get(0).y;
        for (Point p : crossPointList) {
            // y 값이 달라질 때마다 sb을 통해 answer 배열 값 변경
            if (p.y != curY) {
                answer[(int) -(curY - maxY)] = sb.toString();
                curY = p.y;
                sb = new StringBuilder(str.repeat((int) (maxX - minX + 1)));
            }

            sb.replace((int) (p.x - minX), (int) (p.x - minX + 1), "*");
        }

        answer[(int) -(curY - maxY)] = sb.toString();

        return answer;
    }

    public static void main(String[] args) {
        int[][] line = new int[][] {
                { 2, -1, 4 },
                { -2, -1, 4 },
                { 0, -1, 1 },
                { 5, -8, -12 },
                { 5, 8, 12 }
        };

        System.out.println(Arrays.toString(solution(line)));
    }
}
package boj.graph.p3860;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int W, H, G, E;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Hole {
        Point startPoint;
        Point endPoint;
        int time;

        public Hole(Point startPoint, Point endPoint, int time) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p3860/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0)
                break;

            char[][] map = new char[H][W];

            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());

            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                map[Y][X] = '+';    // 묘비 표시
            }

            st = new StringTokenizer(br.readLine());
            E = Integer.parseInt(st.nextToken());

            Hole[] holes = new Hole[E];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int X1 = Integer.parseInt(st.nextToken());
                int Y1 = Integer.parseInt(st.nextToken());
                int X2 = Integer.parseInt(st.nextToken());
                int Y2 = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                map[Y1][X1] = '<';  // 귀신 구멍 입구
                map[Y2][X2] = '>';  // 귀신 구멍 출구

                holes[i] = new Hole(new Point(Y1, X1), new Point(Y2, X2), T);
            }
        }
    }
}

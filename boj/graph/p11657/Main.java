package boj.graph.p11657;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Bus> buses;
    static long[] minTime;  // 음의 간선이 클 경우 underflow가 발생하게 되어 출력초과가 나오는 경우가 있기 때문에 long[]으로 선언한다

    static class Bus {
        int start;
        int dest;
        int time;

        public Bus(int start, int dest, int time) {
            this.start = start;
            this.dest = dest;
            this.time = time;
        }
    }

    static void getMinTime() {
        for (int i = 1; i <= N; i++) {
            for (Bus bus : buses) {
                if ((minTime[bus.start] != Long.MAX_VALUE)
                        && (minTime[bus.start] + bus.time < minTime[bus.dest])) {
                    // N번째 과정에도 값이 변한다면 음의 사이클이 존재한다는 의미이다
                    if (i == N) {
                        System.out.println(-1);
                        return;
                    }
                    else {
                        minTime[bus.dest] = minTime[bus.start] + bus.time;
                    }
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (minTime[i] == Long.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(minTime[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p11657/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        buses = new ArrayList<>();

        minTime = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i == 1)
                minTime[i] = 0;
            else
                minTime[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            buses.add(new Bus(A, B, C));
        }

        getMinTime();
    }
}

package boj.simulation.p21608;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int N, sum;
    static int[][] room;
    static int[] students;
    static Map<Integer, Set<Integer>> favors;

    static class Seat implements Comparable<Seat> {
        int x;
        int y;
        int likeCnt;
        int emptyCnt;

        public Seat(int x, int y, int likeCnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        // 다른 좌석과 비교
        @Override
        public int compareTo(Seat seat) {
            // 조건 1
            if (this.likeCnt != seat.likeCnt)
                return seat.likeCnt - this.likeCnt;

            // 조건 2
            if (this.emptyCnt != seat.emptyCnt)
                return seat.emptyCnt - this.emptyCnt;

            // 조건 3
            if (this.x != seat.x)
                return this.x - seat.x;
            return this.y - seat.y;
        }

    }

    static int getLikeCnt(int x, int y, int idx) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || tx >= N || ty < 0 || ty >= N) {
                continue;
            }

            if (favors.get(idx).contains(room[tx][ty])) {
                count++;
            }
        }

        return count;
    }

    static int getEmptyCnt(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int tx = x + MX[i];
            int ty = y + MY[i];

            if (tx < 0 || tx >= N || ty < 0 || ty >= N) {
                continue;
            }

            if (room[tx][ty] == 0) {
                count++;
            }
        }

        return count;
    }

    static Seat chooseSeat(int idx) {
        Seat seat = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (room[i][j] > 0) {
                    continue;
                }

                Seat cur = new Seat(i, j, getLikeCnt(i, j, idx), getEmptyCnt(i, j));

                if (seat == null || seat.compareTo(cur) > 0) {
                    seat = cur;
                }
            }
        }
        return seat;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p21608/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sum = 0;

        room = new int[N][N];
        students = new int[N * N];
        favors = new HashMap<>();

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());

            students[i] = idx;
            favors.put(idx, new HashSet<>());

            for (int j = 0; j < 4; j++) {
                favors.get(idx).add(Integer.parseInt(st.nextToken()));
            }
        }

        // 1. 학생들 자리 배치
        for (int i = 0; i < students.length; i++) {
            Seat seat = chooseSeat(students[i]);
            room[seat.x][seat.y] = students[i];
        }

        // 2. 학생들의 만족도 총 합
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = getLikeCnt(i, j, room[i][j]);
                if (count > 0) {
                    sum += (int) Math.pow(10, count - 1);
                }
            }
        }

        System.out.println(sum);
    }
}
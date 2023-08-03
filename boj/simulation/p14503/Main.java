package boj.simulation.p14503;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // 북 동 남 서
    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, 1, 0, -1 };

    static int N, M, count;
    static int[][] room;

    private static void clean(int curX, int curY, int curDir) {
        // 현재 칸 청소
        room[curX][curY] = -1;

        for (int i = 0; i < 4; i++) {
            curDir = (curDir + 3) % 4; // 북 -> 서 -> 남 -> 동
            int tx = curX + MX[curDir];
            int ty = curY + MY[curDir];

            if (tx >= 0 && ty >= 0 && tx < N && ty < M && room[tx][ty] == 0) {
                count++;
                clean(tx, ty, curDir);
                return;
            }
        }

        // 네 방향 모두 청소되었거나 벽인 경우
        int back = (curDir + 2) % 4;
        int bx = curX + MX[back];
        int by = curY + MY[back];

        if (bx >= 0 && by >= 0 && bx < N && by < M && room[bx][by] != 1) {
            clean(bx, by, curDir);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p14503/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        count = 1;

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(x, y, dir);

        System.out.println(count);
    }
}

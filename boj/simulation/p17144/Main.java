package boj.simulation.p17144;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, 1, 0, -1 };

    static int R, C, T;
    static int[][] map;
    static int ap1, ap2;

    static int[][] spread() {
        int[][] temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    temp[i][j] = -1;
                    continue;
                }

                temp[i][j] += map[i][j];

                for (int k = 0; k < 4; k++) {
                    int tx = i + MX[k];
                    int ty = j + MY[k];

                    if (tx < 0 || tx >= R || ty < 0 || ty >= C || map[tx][ty] == -1) {
                        continue;
                    }

                    temp[tx][ty] += (map[i][j] / 5);
                    temp[i][j] -= (map[i][j] / 5);
                }
            }
        }

        return temp;
    }

    static void run() {
        map = spread();

        // 반시계 방향으로 진행
        int pos = ap1;
        for (int x = pos - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        for (int x = 0; x < pos; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[pos][y] = map[pos][y - 1];
        }
        map[pos][1] = 0;

        // 시계 방향으로 진행
        pos = ap2;
        for (int x = pos + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
        for (int x = R - 1; x > pos; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[pos][y] = map[pos][y - 1];
        }
        map[pos][1] = 0;
    }

    static void findAP() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                ap1 = i;
                ap2 = i + 1;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p17144/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findAP();

        for (int i = 0; i < T; i++) {
            run();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result += map[i][j];
            }
        }

        // 공기청정기를 나타내는 -1이 두번 더해졌기 때문
        System.out.println(result + 2);
    }
}
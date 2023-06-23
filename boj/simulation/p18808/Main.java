package boj.simulation.p18808;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int answer = 0;
    static int[][] map;

    static int[][] rotate(int[][] sticker, int r, int c) {
        int[][] newSticker = new int[c][r];

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                newSticker[j][r - i - 1] = sticker[i][j];

        return newSticker;
    }

    static boolean putOn(int x, int y, int r, int c, int[][] sticker) {
        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (map[i][j] == 1 && sticker[i - x][j - y] == 1)
                    return false;
            }
        }

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (sticker[i - x][j - y] == 1) {
                    map[i][j] = 1;
                    answer++;
                }
            }
        }

        return true;
    }

    private static void findLocation(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for (int dir = 0; dir < 4; dir++) {
            // 90도 회전
            if (dir != 0)
                sticker = rotate(sticker, r, c);

            r = sticker.length;
            c = sticker[0].length;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 스티커가 노트 밖으로 벗어날 경우
                    if (i + r > N || j + c > M)
                        break;

                    if (putOn(i, j, r, c, sticker)) {
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p18808/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int count = 0; count < K; count++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            findLocation(sticker);
        }

        System.out.println(answer);
    }
}

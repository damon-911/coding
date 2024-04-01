package boj.simulation.p1780;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] result;
    static int[][] paper;

    static boolean check(int startX, int startY, int size) {
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (paper[i][j] != paper[startX][startY]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void dividePaper(int startX, int startY, int size) {
        if (check(startX, startY, size)) {
            int num = paper[startX][startY];
            if (num == -1) {
                result[0]++;
            }
            if (num == 0) {
                result[1]++;
            }
            if (num == 1) {
                result[2]++;
            }
            return;
        }

        int newSize = size / 3;
        for (int i = startX; i < startX + size; i += newSize) {
            for (int j = startY; j < startY + size; j += newSize) {
                dividePaper(i, j, newSize);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p1780/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        result = new int[3];
        paper = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dividePaper(0, 0, N);

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }
}
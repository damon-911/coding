package boj.simulation.p21277;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static int[][] combinedPuzzle = new int[150][150];
    static int[][] puzzle1 = new int[50][50];
    static int[][] puzzle2 = new int[50][50];

    static int N1, M1;
    static int N2, M2;
    static int result = 100 * 100;

    static void matchPuzzles(int x, int y) {
        boolean flag = true;

        for (int i = x; i < x + N1; i++) {
            for (int j = y; j < y + M1; j++) {
                if (combinedPuzzle[i][j] == 1 && puzzle1[i - x][j - y] == 1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }

        if (flag) {
            int minX = Math.min(x, 50);
            int maxX = Math.max(x + N1 - 1, 49 + N2);
            int minY = Math.min(y, 50);
            int maxY = Math.max(y + M1 - 1, 49 + M2);

            int area = (maxX - minX + 1) * (maxY - minY + 1);
            result = Math.min(result, area);
        }
    }

    static void rotate() {
        int[][] rotatedPuzzle = new int[50][50];

        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                rotatedPuzzle[j][N1 - 1 - i] = puzzle1[i][j];
            }
        }

        for (int i = 0; i < 50; i++) {
            System.arraycopy(rotatedPuzzle[i], 0, puzzle1[i], 0, 50);
        }

        int temp = N1;
        N1 = M1;
        M1 = temp;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p21277/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 퍼즐 1
        StringTokenizer st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N1; i++) {
            String line = br.readLine();
            for (int j = 0; j < M1; j++) {
                puzzle1[i][j] = line.charAt(j) - '0';
            }
        }

        // 퍼즐 2
        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N2; i++) {
            String line = br.readLine();
            for (int j = 0; j < M2; j++) {
                puzzle2[i][j] = line.charAt(j) - '0';
            }
        }

        // 퍼즐 2는 가운데에 고정
        for (int i = 50; i < 50 + N2; i++) {
            for (int j = 50; j < 50 + M2; j++) {
                combinedPuzzle[i][j] = puzzle2[i - 50][j - 50];
            }
        }

        // 90도씩 총 4번 돌려보면서 해당 퍼즐의 첫 칸을 (0, 0)부터 (99, 99)까지 위치시키며 비교
        for (int k = 0; k < 4; k++) {
            rotate();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    matchPuzzles(i, j);
                }
            }
        }

        System.out.println(result);
    }
}

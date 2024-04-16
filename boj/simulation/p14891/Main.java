package boj.simulation.p14891;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] gear;

    static void rotation(int idx, int dir) {
        if (dir == 1) {
            int temp = gear[idx][7];
            for (int i = 7; i > 0; i--) {
                gear[idx][i] = gear[idx][i - 1];
            }
            gear[idx][0] = temp;
        } else {
            int temp = gear[idx][0];
            for (int i = 0; i < 7; i++) {
                gear[idx][i] = gear[idx][i + 1];
            }
            gear[idx][7] = temp;
        }
    }

    static void checkLeft(int idx, int dir) {
        if (idx < 0) {
            return;
        }
        if (gear[idx][2] == gear[idx + 1][6]) {
            return;
        }
        checkLeft(idx - 1, -dir);
        rotation(idx, dir);
    }

    static void checkRight(int idx, int dir) {
        if (idx > 3) {
            return;
        }
        if (gear[idx][6] == gear[idx - 1][2]) {
            return;
        }
        checkRight(idx + 1, -dir);
        rotation(idx, dir);
    }

    static void run(int idx, int dir) {
        checkLeft(idx - 1, -dir);
        checkRight(idx + 1, -dir);
        rotation(idx, dir);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p14891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            run(idx, dir);
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += Math.pow(2, i) * gear[i][0];
        }
        System.out.println(result);
    }
}

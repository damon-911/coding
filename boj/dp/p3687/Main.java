package boj.dp.p3687;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p3687/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [0] : 최솟값, [1] : 최댓값
        long[] minDp = new long[101];
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;

        String[] minNum = { "0", "0", "1", "7", "4", "2", "0", "8" };
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String temp = minDp[i - j] + minNum[j];
                minDp[i] = Math.min(Long.parseLong(temp), minDp[i]);
            }
        }

        String[] maxDp = new String[101];
        maxDp[2] = "1";

        String[] maxNum = { "0", "0", "1", "7", "4", "2", "6", "8" };
        for (int i = 3; i <= 100; i++) {
            String temp = "";
            if (i % 2 == 0) {
                for (int j = 0; j < i / 2; j++) {
                    temp += "1";
                }
            } else {
                int value = i / 2 - 1;
                for (int j = 0; j < value; j++) {
                    temp += "1";
                }
                temp = maxNum[i - (value * 2)] + temp;
            }
            maxDp[i] = temp;
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(minDp[N] + " " + maxDp[N]);
        }
    }
}
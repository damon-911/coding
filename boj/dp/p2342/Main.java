package boj.dp.p2342;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int[] move;
    static int[][][] dp;

    private static int energy(int pos, int des) {
        int num = Math.abs(pos - des);
        if (pos == 0)
            return 2;
        else if (num == 0)
            return 1;
        else if (num == 1 || num == 3)
            return 3;
        else
            return 4;
    }

    private static int game(int left, int right, int count) {
        if (count == move.length)
            return 0;

        if (dp[left][right][count] != -1)
            return dp[left][right][count];

        dp[left][right][count] = Math.min(game(move[count], right, count + 1) + energy(left, move[count]),
                game(left, move[count], count + 1) + energy(right, move[count]));

        return dp[left][right][count];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p2342/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nums = br.readLine().split(" ");

        move = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            move[i] = Integer.parseInt(nums[i]);
        }

        dp = new int[5][5][nums.length];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(game(0, 0, 0));
    }
}
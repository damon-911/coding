package boj.sorting.p5052;

import java.io.*;
import java.util.Arrays;

public class Main {

    static boolean check(String[] nums, int N) {
        for (int i = 0; i < N - 1; i++) {
            if (nums[i + 1].startsWith(nums[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p5052/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] nums = new String[N];
            for (int j = 0; j < N; j++) {
                nums[j] = br.readLine();
            }
            Arrays.sort(nums);
            System.out.println(check(nums, N) ? "YES" : "NO");
        }
    }
}
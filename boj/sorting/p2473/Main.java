package boj.sorting.p2473;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] nums;
    static long[] result = new long[3];
    static long diff = Long.MAX_VALUE;

    private static void find(int idx) {
        int left = idx + 1;
        int right = N - 1;

        while (left < right) {
            long sum = nums[left] + nums[right] + nums[idx];

            if (Math.abs(sum) < diff) {
                diff = Math.abs(sum);
                result[0] = nums[left];
                result[1] = nums[right];
                result[2] = nums[idx];
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p2473/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);

        for (int i = 0; i < N - 2; i++) {
            find(i);
        }
        Arrays.sort(result);

        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
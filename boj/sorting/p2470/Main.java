package boj.sorting.p2470;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int[] result = new int[2];
    static int diff = Integer.MAX_VALUE;

    private static void find() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (Math.abs(sum) < diff) {
                diff = Math.abs(sum);
                result[0] = nums[left];
                result[1] = nums[right];
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p2470/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        find();
    }
}
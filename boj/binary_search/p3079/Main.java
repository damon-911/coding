package boj.binary_search.p3079;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long M;
    static long[] nums;
    static long minTime;

    static void findMinTime() {
        long low = 0;
        long high = M * nums[nums.length - 1];

        while (low <= high) {
            long mid = (low + high) / 2;
            long sum = 0;

            for (long num : nums) {
                long count = mid / num;

                sum += count;
                if (sum >= M) {
                    break;
                }
            }

            if (sum >= M) {
                high = mid - 1;
                minTime = Math.min(mid, minTime);
            } else {
                low = mid + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p3079/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        nums = new long[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(nums);

        minTime = M * nums[nums.length - 1];

        findMinTime();

        System.out.println(minTime);
    }
}

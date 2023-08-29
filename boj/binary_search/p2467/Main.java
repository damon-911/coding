package boj.binary_search.p2467;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2467/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        long[] answer = new long[2];
        long min = Long.MAX_VALUE;

        // // 투 포인터 방법
        // int start = 0;
        // int end = N - 1;

        // while (start < end) {
        // long sum = nums[start] + nums[end];

        // if (min > Math.abs(sum)) {
        // min = Math.abs(sum);
        // answer[0] = nums[start];
        // answer[1] = nums[end];
        // }

        // if (sum < 0) {
        // start++;
        // } else if (sum >= 0) {
        // end--;
        // }
        // }

        for (int i = 0; i < N; i++) {
            int start = i + 1;
            int end = N - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                long sum = nums[i] + nums[mid];

                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    answer[0] = nums[i];
                    answer[1] = nums[mid];
                }

                if (sum < 0) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
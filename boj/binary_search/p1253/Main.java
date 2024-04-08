package boj.binary_search.p1253;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int result = 0;

    static void findGOOD() {
        // nums 안에 음수도 있을 수 있다는 것에 유의
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while (true) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }

                if (left >= right)
                    break;

                if (nums[left] + nums[right] > nums[i]) {
                    right--;
                } else if (nums[left] + nums[right] < nums[i]) {
                    left++;
                } else {
                    result++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p1253/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        findGOOD();

        System.out.println(result);
    }
}
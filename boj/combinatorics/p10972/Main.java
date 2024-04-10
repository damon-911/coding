package boj.combinatorics.p10972;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;

    static void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static boolean nextPermutaion() {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] > nums[i]) {
            i--;
        }

        // 마지막 순열인 경우
        if (i == 0) {
            return false;
        }

        int j = nums.length - 1;
        while (nums[i - 1] > nums[j]) {
            j--;
        }

        swap(i - 1, j);

        j = nums.length - 1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p10972/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if (nextPermutaion()) {
            for (int i : nums) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(-1);
        }
    }
}
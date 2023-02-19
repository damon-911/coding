package boj.sorting.p1920;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;

    static void find(int num, int start, int end) {
        // start과 end가 역전되면 존재하지 않는다는 의미이다
        if (start > end) {
            System.out.println("0");
            return;
        }

        // mid를 구해서 이분 탐색을 수행한다
        int mid = (start + end) / 2;
        if (nums[mid] == num) {
            System.out.println("1");
        }
        else if (num < nums[mid]) {
            find(num, start, mid - 1);
        }
        else {
            find(num, mid + 1, end);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p1920/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            find(num, 0, N - 1);
        }
    }
}

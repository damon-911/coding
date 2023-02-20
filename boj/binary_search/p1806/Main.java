package boj.binary_search.p1806;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] nums;
    static int minLength;

    static void twoPointer() {
        int start = 0;
        int end = 0;
        int sum = 0;

        while (start <= N && end <= N) {
            if (sum >= S) {
                // 가장 최소가 되는 길이를 구한다
                minLength = Math.min(minLength, end - start);

                // sum에 nums[start]를 뺴고 start를 하나 전진시킨다
                sum -= nums[start++];
            }
            else {
                // sum에 nums[end]를 더하고 end를 하나 전진시킨다
                sum += nums[end++];
            }
        }

        if (minLength == Integer.MAX_VALUE)
            System.out.println("0");
        else
            System.out.println(minLength);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        minLength = Integer.MAX_VALUE;

        twoPointer();
    }
}
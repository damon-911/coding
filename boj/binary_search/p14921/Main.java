package boj.binary_search.p14921;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    static int findNearestZero() {
        int result = Integer.MAX_VALUE;

        int start = 0;
        int end = N - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];

            if (Math.abs(sum) <= Math.abs(result)) {
                result = sum;
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p14921/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findNearestZero());
    }
}
package boj.binary_search.p2805;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] inputs;
    static int answer, max, left, right, mid;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = 0;
        max = 0;

        st = new StringTokenizer(br.readLine());
        inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
            max = Math.max(inputs[i], max);
        }

        left = 0;
        right = max;
        mid = 0;

        while (true) {
            mid = (left + right) / 2;

            long sum = 0;
            for (int input : inputs) {
                if (input > mid)
                    sum += input - mid;
            }

            if (sum == M) {
                answer = mid;
                break;
            }
            else if (sum > M) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }

            if (right < left)
                break;
        }

        System.out.println(answer);
    }
}

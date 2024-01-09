package boj.binary_search.p1300;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p1300/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long low = 1;
        long high = K;

        while (low < high) {
            long mid = (low + high) / 2;
            long count = 0;

            // N을 초과하지 않는 원소의 개수
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (K <= count) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(low);
    }
}
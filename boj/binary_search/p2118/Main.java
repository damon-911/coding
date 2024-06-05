package boj.binary_search.p2118;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2118/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int max = 0;

        int[] dist = new int[N + 1];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.parseInt(br.readLine());
            max += dist[i];
        }

        int start = 0;
        int end = 0;
        int now = dist[start];
        int result = 0;

        while (start <= end && end < N) {
            int min = Integer.min(now, max - now);

            result = Integer.max(result, min);

            if (now == min) {
                now += dist[++end];
            } else {
                now -= dist[start++];
            }
        }

        System.out.println(result);
    }
}
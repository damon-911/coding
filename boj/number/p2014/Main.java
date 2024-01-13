package boj.number.p2014;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K, N;
    static long[] prime;
    static Queue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2014/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        prime = new long[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            prime[i] = Long.parseLong(st.nextToken());
            pq.add(prime[i]);
        }

        long answer = 0;
        while (N-- > 0) {
            answer = pq.poll();

            for (int i = 0; i < K; i++) {
                long result = answer * prime[i];

                // 2^31보다 작아야 함
                if (result >= ((long) 2 << 30)) {
                    break;
                }

                pq.offer(result);

                // 중복된 값 배제
                if (answer % prime[i] == 0) {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
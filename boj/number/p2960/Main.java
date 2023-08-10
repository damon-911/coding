package boj.number.p2960;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    static void findNum() {
        int count = 0;

        boolean[] isNotPrime = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                if (!isNotPrime[j]) {
                    isNotPrime[j] = true;
                    count++;
                }

                if (count == K) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2960/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        findNum();
    }
}

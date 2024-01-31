package boj.number.p6064;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int M, N, x, y;

    static int calculate() {
        // 나머지 값이 0이 나오는 것을 방지하기 위해 x-1, y-1로 계산
        for (int i = x - 1; i < N * M; i += M) {
            if (i % N == y - 1) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p6064/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            System.out.println(calculate());
        }
    }
}
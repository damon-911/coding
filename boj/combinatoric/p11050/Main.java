package boj.combinatoric.p11050;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    static int pascal(int a, int b) {
        if (a == b || b == 0)
            return 1;
        else
            return pascal(a - 1, b - 1) + pascal(a - 1, b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatoric/p11050/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(pascal(N, K));
    }
}
package boj.combinatorics.p2407;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p2407/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BigInteger sum = BigInteger.ONE;
        BigInteger div = BigInteger.ONE;

        // n! / (n-m)! * m!
        for (int i = 0; i < m; i++) {
            sum = sum.multiply(new BigInteger(String.valueOf(n - i)));
            div = div.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        BigInteger answer = sum.divide(div);

        System.out.println(answer);
    }
}
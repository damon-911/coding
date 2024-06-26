package boj.number.p2436;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2436/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long gcd = Long.parseLong(st.nextToken());
        long lcm = Long.parseLong(st.nextToken());

        // x * y = gcd(x, y) * lcm(x, y)
        long xy = gcd * lcm;

        long x = 0;
        long y = 0;

        for (long i = gcd; i <= Math.sqrt(xy); i += gcd) {
            if (xy % i == 0) {
                long temp = xy / i;
                if (gcd(i, temp) == gcd) {
                    x = i;
                    y = temp;
                }
            }
        }

        System.out.println(x + " " + y);
    }
}
package boj.combinatorics.p11401;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;

    // base : 밑, expo : 지수
    static long calculate(long base, long expo) {
        if (expo == 1) {
            return base % MOD;
        }

        long temp = calculate(base, expo / 2);

        if (expo % 2 == 1) {
            return (temp * temp % MOD) * base % MOD;
        }

        return temp * temp % MOD;
    }

    static long factorial(long num) {
        long temp = 1L;

        while (num > 1) {
            temp = (temp * num) % MOD;
            num--;
        }

        return temp;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p11401/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        // 페르마의 소정리
        // 정수 a와 p가 있고 a가 p의 배수가 아니면서 p가 소수일때 다음이 성립한다
        // a^p ≡ a (mod p) -> a^p mod p ≡ a mod p
        // a^p를 p로 나눈 나머지는 a를 p로 나눈 나머지와 같다
        // a^p ≡ a (mod p) -> a^p mod p ≡ a mod p
        // a^{p-1} ≡ 1 (mod p)
        // a^{p-2} ≡ a^{-1} (mod p)
        // ...
        // N!/(K! * (N - K)!) % MOD

        // 분자 : N!
        long numerator = factorial(N);

        // 분모 : (K! * (N - K)!) % MOD
        long denominator = factorial(K) * factorial(N - K) % MOD;

        // N!/(K! * (N - K)!) % MOD
        // = N! % MOD * ((K! * (N - K)!)^{-1} % MOD) % MOD
        // = N! % MOD * (K! * (N - K)!)^{p-2} % MOD
        System.out.println(numerator * calculate(denominator, MOD - 2) % MOD);
    }
}
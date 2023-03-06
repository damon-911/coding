package boj.number.p3955;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static long A, B;

    static class EGCDResult {
        long s;
        long t;
        long r;

        public EGCDResult(long s, long t, long r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }
    }

    static EGCDResult egcd(long a, long b) {
        long s0 = 1; long t0 = 0; long r0 = a;
        long s1 = 0; long t1 = 1; long r1 = b;
        long temp;

        while (r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1; // r0 % r1
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1; // s0 % s1
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1; // t0 % t1
            t0 = t1;
            t1 = temp;
        }

        return new EGCDResult(s0, t0, r0);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p3955/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            // X : 인당 나눠줄 사탕의 수
            // Y : 사탕 봉지의 수
            // A * X + 1 = B * Y
            // Ax + By = C 의 베주 항등식 형태로 변환
            // A(-x) + By = 1 (추후 k를 구할 때 x의 범위가 반전된다)
            EGCDResult result = egcd(A, B);

            // Ax + By = C 일 때 C % gcd(A, B) == 0 이어야 해를 가질 수 있다 : 베주 항등식
            if (result.r != 1)
                System.out.println("IMPOSSIBLE");
            else {
                // As + Bt = r, Ax + By = C 두 식에서 C와 r을 일치시켜서 x0, y0을 구함 -> 초기해
                // x0 = s * C / r = s
                // y0 = t * C / r = t
                long x0 = result.s;
                long y0 = result.t;

                // 일반해 공식
                // x = x0 + B / gcd * k
                // y = y0 - A / gcd * k

                // x < 0
                // x0 + B * k < 0
                // k < - x0 / B
                long kFromX = (long) (Math.ceil((double) -x0 / (double) B) - 1);

                // 0 < y <= 1e9
                // 0 < y0 - A * k <= 1e9
                // (y0 - 1e9) / A <= k < y0 / A
                long kFromY = (long) (Math.ceil((double) y0 / (double) A) - 1);
                long kLimitFromY = (long) Math.ceil((double) (y0 -1e9) / (double) A);

                long k = Math.min(kFromX, kFromY);
                if (k >= kLimitFromY) {
                    System.out.println(y0 - A * k);
                }
                else
                    System.out.println("IMPOSSIBLE");
            }
        }
    }
}

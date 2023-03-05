package boj.number.p14476;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;
    static int[] LtoR, RtoL;

    // 최대공약수 구하는 함수
    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        LtoR = new int[N];
        LtoR[0] = nums[0];
        for (int i = 1; i < N; i++) {
            LtoR[i] = gcd(LtoR[i - 1], nums[i]);
        }

        RtoL = new int[N];
        RtoL[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            RtoL[i] = gcd(RtoL[i + 1], nums[i]);
        }

        int[] result = new int[N];
        result[0] = RtoL[1];
        result[N - 1] = LtoR[N - 2];
        for (int i = 1; i < N - 1; i++) {
            result[i] = gcd(LtoR[i - 1], RtoL[i + 1]);
        }

        int answer = 0;
        int except = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] % result[i] != 0 && answer < result[i]) {
                answer = result[i];
                except = nums[i];
            }
        }

        if (answer == 0)
            System.out.println(-1);
        else
            System.out.println(answer + " " + except);
    }
}

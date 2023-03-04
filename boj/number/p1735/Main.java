package boj.number.p1735;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C, D;

    static int getGcd(int a, int b) {
        if (a % b == 0)
            return b;
        else
            return getGcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p1735/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int temp1 = A * D + B * C;
        int temp2 = B * D;
        int gcd = getGcd(temp1, temp2);

        System.out.println(temp1 / gcd + " " + temp2 / gcd);
    }
}
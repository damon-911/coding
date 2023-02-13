package boj.number.p1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[] P;
    static int K;
    static boolean[] checked;
    static int smaller = 0;

    static void divide(char[] P, int divider) {
        int r = 0;
        for (char c : P) {
            int pos = c - '0';
            r = (pos + r * 10) % divider;
        }

        if (r == 0)
            smaller = divider;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p1837/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[K + 1];
        for (int i = 2; i <= K; i++) {
            if (!checked[i]) {
                for (int j = i * 2; j <= K; j += i) {
                    checked[j] = true;
                }
            }
        }

        for (int i = 2; i < K; i++) {
            if (!checked[i])
                divide(P, i);

            if (smaller != 0)
                break;
        }

        if (smaller == 0)
            System.out.println("GOOD");
        else
            System.out.println("BAD " + smaller);
    }
}
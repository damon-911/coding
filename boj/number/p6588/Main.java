package boj.number.p6588;

import java.io.*;

public class Main {

    static final int MAX = 1_000_000;

    static boolean[] isPrime;

    static void findPrime() {
        isPrime = new boolean[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= MAX; i++) {
            for (int j = i * 2; j <= MAX; j += i) {
                if (!isPrime[j]) {
                    continue;
                }
                isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p6588/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        findPrime();

        while (true) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                break;
            }

            boolean flag = false;

            for (int i = 2; i <= num / 2; i++) {
                if (isPrime[i] && isPrime[num - i]) {
                    System.out.println(num + " = " + i + " + " + (num - i));
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}
package boj.number.p2904;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 1000000;

    static int N;
    static List<Integer> primeList;
    static int[][] primeCnt;

    static int[] visited = new int[MAX + 1];
    static boolean[] isPrime = new boolean[MAX + 1];

    static int power(int x, int y) {
        int result = 1;

        while (y > 0) {
            if (y % 2 == 1) {
                result = result * x;
            }
            y /= 2;
            x = x * x;
        }

        return result;
    }

    static void findPrime() {
        for (int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= MAX; i++) {
            if (!isPrime[i])
                continue;

            for (int j = i * i; j <= MAX; j += i) {
                isPrime[j] = false;
            }
        }
    }

    static void findGCD() {
        int gcd = 1;
        int cnt = 0;

        for (int i = 0; i < primeList.size(); i++) {
            int d = visited[primeList.get(i)] / N; // 최소로 분배되어야 할 소수의 개수

            for (int j = 0; j < N; j++) {
                if (primeCnt[j][i] < d) {
                    cnt += d - primeCnt[j][i];
                }
            }

            gcd *= power(primeList.get(i), d);
        }

        System.out.print(gcd + " ");
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p2904/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        findPrime();

        primeList = new ArrayList<>();
        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i])
                primeList.add(i);
        }

        primeCnt = new int[N][primeList.size()]; // [i][j] : i번쨰 숫자에 사용되는 소수 j의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < primeList.size(); j++) {
                if (num == 1)
                    break;

                while (num % primeList.get(j) == 0) {
                    num /= primeList.get(j);
                    visited[primeList.get(j)]++;
                    primeCnt[i][j]++;
                }
            }
        }

        findGCD();
    }
}
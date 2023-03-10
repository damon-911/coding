package boj.number.p1644;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static boolean[] prime;
    static List<Integer> primeList;
    static int answer;

    static void setPrime() {
        // 에라토스테네스의 체를 이용해 소수를 구한다
        for (int i = 2; i * i <= N; i++) {
            // i가 소수라면
            if (!prime[i]) {
                // i의 배수는 모두 소수는 아니다
                for (int j = i * 2; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }

        // 소수만 리스트에 옮긴다
        for (int i = 2; i <= N; i++) {
            if (!prime[i])
                primeList.add(i);
        }
    }

    static void findSum() {
        // primeList : 정렬되어 있는 리스트
        // 투 포인터를 사용해 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구한다
        int start = 0;
        int end = 0;
        int sum = 0;

        while (start < primeList.size()) {
            if (sum >= N || end == primeList.size()) {
                sum -= primeList.get(start++);
            }
            else {
                sum += primeList.get(end++);
            }

            if (sum == N)
                answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p1644/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        prime = new boolean[N + 1];
        primeList = new ArrayList<>();

        // 소수는 false로 표시, 0과 1은 소수가 아니다
        prime[0] = true;
        prime[1] = true;

        setPrime();

        findSum();

        System.out.println(answer);
    }
}
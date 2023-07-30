package boj.combinatorics.p5568;

import java.io.*;
import java.util.HashSet;

public class Main {

    static int N, K;
    static String[] cards;
    static HashSet<String> hashSet;

    private static void makeNum(String num, boolean[] visited, int depth, int limit) {
        if (depth == limit) {
            hashSet.add(num);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                makeNum(num + cards[i], visited, depth + 1, limit);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p5568/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        cards = new String[N];
        hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            cards[i] = br.readLine();
        }

        makeNum("", new boolean[N], 0, K);

        System.out.println(hashSet.size());
    }
}
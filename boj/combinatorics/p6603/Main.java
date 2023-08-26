package boj.combinatorics.p6603;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static void comb(int[] S, boolean[] visited, int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < S.length; i++) {
                if (visited[i])
                    System.out.print(S[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < S.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb(S, visited, i, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p6603/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int K = Integer.parseInt(st.nextToken());
            if (K == 0)
                break;

            int[] S = new int[K];
            for (int i = 0; i < K; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            comb(S, new boolean[K], 0, 0);
            System.out.println();
        }
    }
}
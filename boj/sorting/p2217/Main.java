package boj.sorting.p2217;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p2217/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] ropes = new Integer[N];
        for (int i = 0; i < ropes.length; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes, Collections.reverseOrder());

        int max = ropes[0];
        for (int i = 1; i < N; i++) {
            int result = ropes[i] * (i + 1);
            max = Math.max(max, result);
        }

        System.out.println(max);
    }
}
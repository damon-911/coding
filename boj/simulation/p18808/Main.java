package boj.simulation.p18808;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p18808/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
}

package boj.dp.p1932;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;

    static void findMaxPath() {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++)
                arr[i - 1][j] += Math.max(arr[i][j], arr[i][j + 1]);
        }

        System.out.println(arr[0][0]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p1932/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][];
        for (int i = 0; i < N; i++) {
            arr[i] = new int[i + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMaxPath();
    }
}

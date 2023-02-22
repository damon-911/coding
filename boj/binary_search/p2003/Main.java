package boj.binary_search.p2003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, num;
    static int[] inputs;

    static void twoPointer() {
        int sum = 0;
        int start = 0;
        int end = 0;

        while (start < N) {
            // sum이 M보다 크거나 같고 end가 배열의 끝에 도달했을 경우
            if (sum >= M || end == N)
                sum -= inputs[start++];
            // sum이 M보다 작을 경우
            else
                sum += inputs[end++];

            // sum이 M과 같을 경우
            if (sum == M)
                num++;
        }

        System.out.println(num);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inputs = new int[N];
        for (int i = 0; i < N; i++)
            inputs[i] = Integer.parseInt(st.nextToken());

        num = 0;

        twoPointer();
    }
}
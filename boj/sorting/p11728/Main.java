package boj.sorting.p11728;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p11728/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int pt_A = 0;
        int pt_B = 0;

        StringBuilder sb = new StringBuilder();

        while (pt_A < N && pt_B < M) {
            if (A[pt_A] <= B[pt_B]) {
                sb.append(A[pt_A++] + " ");
            } else {
                sb.append(B[pt_B++] + " ");
            }
        }

        // 혹시 한쪽이 남았을 경우 나머지 모두 저장
        if (pt_A != N) {
            for (int i = pt_A; i < N; i++) {
                sb.append(A[i] + " ");
            }
        }
        if (pt_B != M) {
            for (int i = pt_B; i < M; i++) {
                sb.append(B[i] + " ");
            }
        }

        System.out.println(sb);
    }
}
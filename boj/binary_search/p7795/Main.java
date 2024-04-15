package boj.binary_search.p7795;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int findPair(int[] A, int[] B) {
        int result = 0;

        for (int i = 0; i < A.length; i++) {
            int index = 0;
            int left = 0;
            int right = B.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (B[mid] < A[i]) {
                    left = mid + 1;
                    index = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            result += index;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p7795/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(A);

            int[] B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                B[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(B);

            System.out.println(findPair(A, B));
        }
    }
}

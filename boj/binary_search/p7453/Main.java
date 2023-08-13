package boj.binary_search.p7453;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A, B, C, D;
    static int[] AB, CD;

    static void findZero() {
        long result = 0;

        int left = 0;
        int right = N * N - 1;

        while (left < N * N && right >= 0) {
            if (AB[left] + CD[right] < 0) {
                left++;
            } else if (AB[left] + CD[right] > 0) {
                right--;
            } else {
                long leftCount = 1;
                long rightCount = 1;

                // AB에 해당 원소와 같은 원소가 몇 개 있는지 카운트
                while (left < N * N - 1 && (AB[left] == AB[left + 1])) {
                    leftCount++;
                    left++;
                }

                // CD에 해당 원소와 같은 원소가 몇 개 있는지 카운트
                while (right > 0 && (CD[right] == CD[right - 1])) {
                    rightCount++;
                    right--;
                }

                result += leftCount * rightCount;
                left++;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p7453/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        AB = new int[N * N];
        CD = new int[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        findZero();
    }
}

package boj.binary_search.p8983;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M, N, L;
    static int result = 0;
    static int[] spots;
    static int[][] animals;

    private static int search(int idx) {
        int start = 0;
        int end = M - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            int dist = Math.abs(animals[idx][0] - spots[mid]) + animals[idx][1];

            if (dist <= L) {
                return 1;
            }

            if (animals[idx][0] < spots[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p8983/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        spots = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            spots[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(spots);

        animals = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (animals[i][1] > L) {
                continue;
            }
            result += search(i);
        }

        System.out.println(result);
    }
}

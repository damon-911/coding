package boj.binary_search.p10816;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] cards;

    private static int lowerBound(int target) {
        int low = 0;
        int high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (target <= cards[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int upperBound(int target) {
        int low = 0;
        int high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (target < cards[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p10816/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(upperBound(num) - lowerBound(num));
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}

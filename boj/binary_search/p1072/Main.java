package boj.binary_search.p1072;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long X, Y, Z;

    static long getPercent(long x, long y) {
        return y * 100 / x;
    }

    static void binarySearch() {
        long start = 1;
        long end = X;
        long mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (getPercent(X + mid, Y + mid) > Z) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());
        Z = getPercent(X, Y);

        // 99%와 100%는 확률이 변하지 않는다
        if (Z >= 99)
            System.out.println("-1");
        else {
            binarySearch();
        }
    }
}
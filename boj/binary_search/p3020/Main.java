package boj.binary_search.p3020;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int binarySearch(int left, int right, int num, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr.length - right;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p3020/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[N / 2];
        int[] up = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);

        int min = N;
        int cnt = 0;

        for (int i = 1; i <= H; i++) {
            int result = binarySearch(0, N / 2, i, down) + binarySearch(0, N / 2, H - i + 1, up);
            if (min == result) {
                cnt++;
                continue;
            } else if (min > result) {
                min = result;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }
}

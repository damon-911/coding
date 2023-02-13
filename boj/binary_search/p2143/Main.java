package boj.binary_search.p2143;

import java.io.*;
import java.util.*;

public class Main {

    static long T;
    static int n, m;
    static long[] arr_n, arr_m;
    static long answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr_n = new long[n];
        for (int i = 0; i < n; i++) {
            arr_n[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr_m = new long[m];
        for (int i = 0; i < m; i++) {
            arr_m[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> sum_n = new ArrayList<>();
        List<Long> sum_m = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long sum = arr_n[i];
            sum_n.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += arr_n[j];
                sum_n.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = arr_m[i];
            sum_m.add(sum);
            for (int j = i + 1; j < m; j++) {
                sum += arr_m[j];
                sum_m.add(sum);
            }
        }

        Collections.sort(sum_n);
        Collections.sort(sum_m, Comparator.reverseOrder());

        int pt_n = 0;
        int pt_m = 0;

        while (true) {
            long current_n = sum_n.get(pt_n);
            long target = T - current_n;

            if (sum_m.get(pt_m) > target) {
                pt_m++;
            }
            else if (sum_m.get(pt_m) < target) {
                pt_n++;
            }
            else {
                long count_n = 0;
                long count_m = 0;

                while (pt_n < sum_n.size() && sum_n.get(pt_n) == current_n) {
                    pt_n++;
                    count_n++;
                }
                while (pt_m < sum_m.size() && sum_m.get(pt_m) == target) {
                    pt_m++;
                    count_m++;
                }

                answer += count_n * count_m;
            }

            if (pt_n == sum_n.size() || pt_m == sum_m.size())
                break;
        }

//        sum_n = new long[n*(n+1)/2];
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            sum_n[count] = arr_n[i];
//            count++;
//
//            for (int j = i + 1; j < n; j++) {
//                sum_n[count] = sum_n[count - 1] + arr_n[j];
//                count++;
//            }
//        }
//        Arrays.sort(sum_n);
//
//        sum_m = new long[m*(m+1)/2];
//        count = 0;
//        for (int i = 0; i < m; i++) {
//            sum_m[count] = arr_m[i];
//            count++;
//
//            for (int j = i + 1; j < m; j++) {
//                sum_m[count] = sum_m[count - 1] + arr_m[j];
//                count++;
//            }
//        }
//        Arrays.sort(sum_m);
//
//        int pt_n = 0;
//        int pt_m = m*(m+1)/2 - 1;
//
//        while (true) {
//            long sum = sum_n[pt_n] + sum_m[pt_m];
//            if (sum == T) {
//                long current_n = sum_n[pt_n];
//                long current_m = T - current_n;
//                long count_n = 0;
//                long count_m = 0;
//
//                while (pt_n < m*(m+1)/2 && sum_n[pt_n] == current_n) {
//                    pt_n++;
//                    count_n++;
//                }
//                while (pt_m > -1 && sum_m[pt_m] == current_m) {
//                    pt_m++;
//                    count_m++;
//                }
//
//                answer += count_n * count_m;
//            }
//            else if (sum > T) {
//                pt_m--;
//            }
//            else {
//                pt_n++;
//            }
//
//            if (pt_n == m*(m+1)/2 || pt_m == -1)
//                break;
//        }

        System.out.println(answer);
    }
}

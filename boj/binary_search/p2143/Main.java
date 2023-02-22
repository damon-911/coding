package boj.binary_search.p2143;

import java.io.*;
import java.util.*;

public class Main {

    static long T;
    static int n, m;
    static long[] arrA, arrB;
    static long answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arrA = new long[n];
        for (int i = 0; i < n; i++) {
            arrA[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arrB = new long[m];
        for (int i = 0; i < m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> sumA = new ArrayList<>();
        List<Long> sumB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long sum = arrA[i];
            sumA.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += arrA[j];
                sumA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            long sum = arrB[i];
            sumB.add(sum);
            for (int j = i + 1; j < m; j++) {
                sum += arrB[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB, Comparator.reverseOrder());

        int ptA = 0;
        int ptB = 0;

        while (ptA < sumA.size() && ptB < sumB.size()) {
            long currentA = sumA.get(ptA);
            long currentB = sumB.get(ptB);

            if (currentA + currentB > T) {
                ptB++;
            }
            else if (currentA + currentB < T) {
                ptA++;
            }
            else {
                long countA = 0;
                long countB = 0;

                // 같은 수가 있는지 확인하고 있다면 그만큼 countA를 증가시킨다
                while (ptA < sumA.size() && sumA.get(ptA) == currentA) {
                    ptA++;
                    countA++;
                }

                // 같은 수가 있는지 확인하고 있다면 그만큼 countB를 증가시킨다
                while (ptB < sumB.size() && sumB.get(ptB) == currentB) {
                    ptB++;
                    countB++;
                }

                // A 배열에서 currentA과 같은 수 * B 배열에서 currentB과 같은 수
                answer += countA * countB;
            }
        }

        System.out.println(answer);
    }
}

package boj.binary_search.p2096;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] maxDP, minDP, temp;
    static int max, min;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2096/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        maxDP = new int[3];
        minDP = new int[3];
        temp = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            // 초기 값 설정
            if (i == 0) {
                maxDP[0] = num1;
                maxDP[1] = num2;
                maxDP[2] = num3;

                minDP[0] = num1;
                minDP[1] = num2;
                minDP[2] = num3;

                continue;
            }

            // 최댓값 DP 값을 구해서 임시 배열인 temp에 담고 나중에 한번에 옮긴다
            temp[0] = num1 + Math.max(maxDP[0], maxDP[1]);
            temp[1] = num2 + Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
            temp[2] = num3 + Math.max(maxDP[1], maxDP[2]);

            maxDP[0] = temp[0];
            maxDP[1] = temp[1];
            maxDP[2] = temp[2];

            // 최솟값 DP 값을 구해서 임시 배열인 temp에 담고 나중에 한번에 옮긴다
            temp[0] = num1 + Math.min(minDP[0], minDP[1]);
            temp[1] = num2 + Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
            temp[2] = num3 + Math.min(minDP[1], minDP[2]);

            minDP[0] = temp[0];
            minDP[1] = temp[1];
            minDP[2] = temp[2];
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDP[i]);
            min = Math.min(min, minDP[i]);
        }

        System.out.println(max + " " + min);
    }
}
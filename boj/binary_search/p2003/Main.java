package boj.binary_search.p2003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, num;
    static int[] inputs;
    static int sum, left, right;
    static Boolean rightFlag;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        inputs = new int[N];
        for (int i = 0; i < N; i++)
            inputs[i] = Integer.parseInt(st.nextToken());

        sum = 0;
        left = 0;
        right = 0;
        rightFlag = true;

        while (right < N) {
            if (rightFlag)
                sum += inputs[right];
            else
                sum -= inputs[left - 1];

            if (sum < M) {
                right++;
                rightFlag = true;
            }
            else if (sum == M) {
                num++;
                left++;
                rightFlag = false;
            } else {
                left++;
                rightFlag = false;
            }
        }

        System.out.println(num);
    }
}
package boj.sorting.p1931;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] time;

    static void findMax() {
        int max = 0;
        int prevTime = 0;

        for (int i = 0; i < N; i++) {
            if (prevTime <= time[i][0]) {
                prevTime = time[i][1];
                max++;
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p1931/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        time = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        // 종료 시간을 기준으로 오름차순 정렬
        // 종료 시간이 같다면 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] != t2[1] ? t1[1] - t2[1] : t1[0] - t2[0];
            }
        });

        findMax();
    }
}

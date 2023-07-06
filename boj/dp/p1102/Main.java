package boj.dp.p1102;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, P;
    static int maxVal = Integer.MAX_VALUE / 2;
    static int[][] costs;
    static int[][] minVal = new int[17][65537]; // 현재 i개의 발전소가 동작하고 있고 j 상태일 경우, 적어도 P개의 발전소가 고장나 있지 않도록 하는 가장 적은 비용

    private static int restart(int cnt, int pNum) {
        if (cnt >= P)
            return 0;

        if (minVal[cnt][pNum] != maxVal)
            return minVal[cnt][pNum];

        for (int i = 0; i < N; i++) {
            // i번 발전소가 켜져있을 때
            if ((pNum & (1 << i)) == (1 << i)) {
                for (int j = 0; j < N; j++) {
                    // 같은 번호의 발전소인 경우 || j번 발전소도 켜져있는 경우 스킵
                    if ((i == j) || (pNum & (1 << j)) == (1 << j))
                        continue;

                    // 최소값 구하기
                    minVal[cnt][pNum] = Math.min(minVal[cnt][pNum], restart(cnt + 1, pNum | (1 << j)) + costs[i][j]);
                }
            }
        }

        return minVal[cnt][pNum];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p1102/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        costs = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(minVal[i], maxVal);
        }

        String status = br.readLine();
        int cnt = 0;
        int pNum = 0;
        for (int i = 0; i < status.length(); i++) {
            if (status.charAt(i) == 'Y') {
                pNum = pNum | (1 << i);
                cnt++;
            }
        }

        P = Integer.parseInt(br.readLine());

        int res = restart(cnt, pNum);

        System.out.println(res == maxVal ? -1 : res);
    }
}
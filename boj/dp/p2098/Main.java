package boj.dp.p2098;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] cost = new int[17][17];
    static int[][] minVal = new int[17][65537];
    static int maxVal = Integer.MAX_VALUE / 2;
    static int fullVisit;
    static int N;

    public static int goTravel(int visitmap, int current) {

        // 전순회하는데 성공하였는가?
        if (visitmap == fullVisit) {
            // 시작점으로 도달하는 루트가 있다면
            if (cost[current][0] > 0) {
                // 그 시작점으로 도달하는 루트를 리턴
                return cost[current][0];
            } else {
                // 도달하지 못한다면 도달불가 표시
                return maxVal / 2;
            }
        }

        // 내 후행 루트가 이미 계산되었는가?
        // 후행 루트 => 나를 current 삼아 전도시를 순회하고 얻은 최단거리를 갱신하여 놓았는가?
        // 한번 계산이 끝났다면 최단거리임이 자명
        if (minVal[current][visitmap] != maxVal)
            return minVal[current][visitmap];

        // 각 도시들을 한번씩 방문해 보자.
        for (int nexti = 1; nexti < N; nexti++) {
            // 도달가능 여부 판단
            if (cost[current][nexti] > 0) {
                // 현재 점이 아니고, 방문하지 않은 도시일때
                if (nexti != current && ((1 << nexti) & visitmap) == 0) {

                    // 후행에 대해 모든 최단거리를 계산한 뒤 걸리는 cost 더하여 준다.
                    // min(gotraval) 한다음에 cost를 더해도 된다.
                    int temp = goTravel(visitmap | (1 << nexti), nexti) + cost[current][nexti];

                    // 최단 비용일 경우 이를 갱신하여 준다.
                    if (minVal[current][visitmap] > temp) {
                        minVal[current][visitmap] = temp;
                    }
                }
            }
        }

        // 얻어낸 최단 비용을 리턴
        return minVal[current][visitmap];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("boj/dp/p2098/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        fullVisit = (1 << N) - 1;

        // dp를 무엇으로 세팅할것인가?
        // dp[cur][visitmap] : 내가 cur점에 있고 visitmap를 방문한 상태일 때
        // 남은 모든 도시들을 순회한 뒤 출발 도시로 돌아갈 때
        // 얻을 수 있는 최단 거리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(minVal[i], maxVal);
        }

        int ans = goTravel(1, 0);
        if (ans == maxVal)
            System.out.println("-1");
        else
            System.out.println(ans);
    }
}
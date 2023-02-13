package boj.graph.p2458;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] diff;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p2458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        diff = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    diff[i][j] = 0;
                else
                    diff[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 키 차이를 확인할 수 있다는 표시
            diff[a][b] = 1;
        }

        // for 문을 3번 돌려서 모든 정점에서 모든 정점으로 가는 최단거리를 구한다 (전체 쌍 최단 경로) -> 키 차이 비교 가능 유무 확인
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if ((i != j) && (diff[i][k] != Integer.MAX_VALUE) && (diff[k][j] != Integer.MAX_VALUE)) {
                        if (diff[i][j] > diff[i][k] + diff[k][j]) {
                            diff[i][j] = diff[i][k] + diff[k][j];
                        }
                    }
                }
            }
        }

        // 자신이 키가 몇 번째인지 알 수 있는 학생의 수
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            boolean flag = true;
            for (int j = 1; j <= N; j++) {
                // 서로 키 차이를 확인할 수 있는지 체크
                if (diff[i][j] == Integer.MAX_VALUE) {
                    if (diff[j][i] == Integer.MAX_VALUE)
                        flag = false;
                }
            }

            if (flag)
                answer++;
        }

        System.out.println(answer);
    }
}

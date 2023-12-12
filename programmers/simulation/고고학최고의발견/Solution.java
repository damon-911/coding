package programmers.simulation.고고학최고의발견;

public class Solution {

    static final int[] MX = { 0, 0, 0, 1, -1 };
    static final int[] MY = { 0, 1, -1, 0, 0 };

    static int N, answer;
    static int[] rotate;
    static int[][] clockBoard;

    // 첫번째 줄의 0 ~ (N-1)번째 칼럼의 회전수 정하기
    static void dfs(int idx) {
        if (idx == N) {
            int[][] temp = new int[N][N];
            int[] cur = new int[N];

            for (int i = 0; i < N; i++) {
                cur[i] = rotate[i];
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[i][j] = clockBoard[i][j];
                }
            }

            int result = 0;

            for (int i = 0; i < N; i++) {
                // 특정 row에 대해 시계 돌려줌
                for (int j = 0; j < N; j++) {
                    result += cur[j];
                    for (int d = 0; d < 5; d++) {
                        int X = i + MX[d];
                        int Y = j + MY[d];

                        if (X < 0 || Y < 0 || X >= N || Y >= N)
                            continue;

                        temp[X][Y] = (temp[X][Y] - cur[j] >= 0) ? (temp[X][Y] - cur[j]) : (temp[X][Y] - cur[j] + 4);
                    }
                }

                // 다음 row가 돌려야할 횟수
                for (int j = 0; j < N; j++) {
                    cur[j] = temp[i][j];
                }
            }

            // 마지막 열의 상태 판별하여 결과 계산
            boolean flag = true;
            for (int i = 0; i < N; i++) {
                if (cur[i] != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                answer = Math.min(answer, result);

            return;
        }

        // 회전 수 정하기
        for (int i = 0; i < 4; i++) {
            rotate[idx] = i;
            dfs(idx + 1);
        }
    }

    public static int solution(int[][] clockHands) {
        answer = Integer.MAX_VALUE;

        N = clockHands.length;
        rotate = new int[N];
        clockBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // clockBoard[i][j]에는 12시를 가리키기 위해 필요한 회전 수가 저장됩니다.
                clockBoard[i][j] = (4 - clockHands[i][j] == 4) ? 0 : 4 - clockHands[i][j];
            }
        }

        dfs(0);

        return answer;
    }

    public static void main(String[] args) {
        int[][] clockHands = {
                { 0, 3, 3, 0 },
                { 3, 2, 2, 3 },
                { 0, 3, 2, 0 },
                { 0, 3, 3, 3 }
        };

        System.out.println(solution(clockHands));
    }
}
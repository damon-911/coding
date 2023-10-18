package programmers.array.자물쇠와열쇠;

public class Solution {

    static int M, N;
    static int[][] map;

    static void rotate(int[][] key) {
        int[][] temp = new int[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = key[M - j - 1][i];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                key[i][j] = temp[i][j];
            }
        }
    }

    static boolean check(int[][] key) {
        int len = map.length;

        for (int i = 0; i < len - (M - 1); i++) {
            for (int j = 0; j < len - (M - 1); j++) {
                // temp에 key를 더함
                for (int k = 0; k < M; k++) {
                    for (int l = 0; l < M; l++) {
                        map[i + k][j + l] += key[k][l];
                    }
                }

                // lock 부분이 전부 1이 되었는지 확인
                boolean flag = true;
                for (int k = M - 1; k < N + M - 1; k++) {
                    for (int l = M - 1; l < N + M - 1; l++) {
                        if (map[k][l] != 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag)
                        break;
                }

                if (flag)
                    return true;

                for (int k = 0; k < M; k++) {
                    for (int l = 0; l < M; l++) {
                        map[i + k][j + l] -= key[k][l];
                    }
                }
            }
        }

        return false;
    }

    public static boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        map = new int[N + (M - 1) * 2][N + (M - 1) * 2];

        // 확장시킨 배열에 Lock 표시
        for (int i = M - 1; i < N + M - 1; i++) {
            for (int j = M - 1; j < N + M - 1; j++) {
                map[i][j] = lock[i - (M - 1)][j - (M - 1)];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (check(key)) {
                return true;
            }
            rotate(key); // 시계 방향으로 90도 회전
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] key = {
                { 0, 0, 0 },
                { 1, 0, 0 },
                { 0, 1, 1 }
        };
        int[][] lock = {
                { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 0, 1 }
        };

        System.out.println(solution(key, lock));
    }
}
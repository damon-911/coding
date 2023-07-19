package programmers.kakao2022.양궁대회;

import java.util.Arrays;

public class Solution {

    static int max = -1;
    static int[] ryan;
    static int[] answer = { -1 };

    private static void dfs(int count, int N, int[] info) {
        if (count == N) {
            // 점수 계산 비교
            int apeach_point = 0;
            int ryan_point = 0;

            for (int i = 0; i <= 10; i++) {
                if (info[i] != 0 || ryan[i] != 0) {
                    if (info[i] < ryan[i])
                        ryan_point += 10 - i;
                    else
                        apeach_point += 10 - i;
                }
            }

            if (ryan_point > apeach_point) {
                if (ryan_point - apeach_point >= max) {
                    answer = ryan.clone();
                    max = ryan_point - apeach_point;
                }
            }

            return;
        }

        for (int i = 0; i <= 10 && ryan[i] <= info[i]; i++) {
            ryan[i]++;
            dfs(count + 1, N, info);
            ryan[i]--;
        }
    }

    public static int[] solution(int n, int[] info) {
        ryan = new int[11];

        dfs(0, n, info);

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

        System.out.println(Arrays.toString(solution(n, info)));
    }
}
package boj.binary_search.p2096;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] nums, maxDP, minDP;
    static int max, min;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2096/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 양 옆에 여백을 만든다
        nums = new int[N + 1][5];
        maxDP = new int[N + 1][5];
        minDP = new int[N + 1][5];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());

                // 양 옆의 여백을 인접한 원소로 채운다
                if (j == 1)
                    nums[i][0] = nums[i][j];
                else if (j == 3)
                    nums[i][4] = nums[i][j];
            }

            for (int j = 1; j <= 3; j++) {
                maxDP[i][j] = Math.max(nums[i][j] + maxDP[i - 1][j - 1], Math.max(nums[i][j] + maxDP[i - 1][j], nums[i][j] + maxDP[i - 1][j + 1]));
                minDP[i][j] = Math.min(nums[i][j] + minDP[i - 1][j - 1], Math.min(nums[i][j] + minDP[i - 1][j], nums[i][j] + minDP[i - 1][j + 1]));

                // 양 옆의 여백을 인접한 원소로 채운다
                if (j == 1) {
                    maxDP[i][0] = maxDP[i][j];
                    minDP[i][0] = minDP[i][j];
                }
                else if (j == 3) {
                    maxDP[i][4] = maxDP[i][j];
                    minDP[i][4] = minDP[i][j];
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            max = Math.max(max, maxDP[N][i]);
            min = Math.min(min, minDP[N][i]);
        }

        System.out.println(max + " " + min);
    }
}
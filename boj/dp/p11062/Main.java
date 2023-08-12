package boj.dp.p11062;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] cards;
    static int[][][] dp;

    public static int game(int turn, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (dp[turn][start][end] != 0) {
            return dp[turn][start][end];
        }

        if (turn == 0) { // 근우 차례인 경우는 왼쪽 카드를 뽑았을 떄와 오른쪽 카드를 뽑았을 때 중 숫자의 합이 크도록 설정
            dp[turn][start][end] = Math.max(game(turn + 1, start + 1, end) + cards[start],
                    game(turn + 1, start, end - 1) + cards[end]);
        } else { // 명우 차례인 경우는 왼쪽 카드를 뽑았을 때와 오른쪽 카드를 뽑았을 때 중 숫자의 합이 작도록 설정
            dp[turn][start][end] = Math.min(game(turn - 1, start + 1, end), game(turn - 1, start, end - 1));
        }

        return dp[turn][start][end];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p11062/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            cards = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[2][N + 1][N + 1]; // 근우와 명우의 차례, 카드의 맨 왼쪽, 카드의 맨 오른쪽

            System.out.println(game(0, 1, N));
        }
    }
}

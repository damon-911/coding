package boj.simulation.p12100;

import java.io.*;
import java.util.*;

public class Main {

    static int[] MX = { -1, 1, 0, 0 };
    static int[] MY = { 0, 0, -1, 1 };

    static int N, max;
    static int[][] board;

    private static void move(int count) {
        if (count == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
            return;
        }

        int copy[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            copy[i] = board[i].clone();

        for (int k = 0; k < 4; k++) {
            switch (k) {
                case 0:
                    // 위로 이동
                    for (int i = 0; i < N; i++) {
                        int index = 0;
                        int block = 0;
                        for (int j = 0; j < N; j++) {
                            if (board[j][i] != 0) {
                                if (block == board[j][i]) {
                                    board[index - 1][i] = block * 2;
                                    block = 0;
                                    board[j][i] = 0;
                                } else {
                                    block = board[j][i];
                                    board[j][i] = 0;
                                    board[index][i] = block;
                                    index++;
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    // 아래로 이동
                    for (int i = 0; i < N; i++) {
                        int index = N - 1;
                        int block = 0;
                        for (int j = N - 1; j >= 0; j--) {
                            if (board[j][i] != 0) {
                                if (block == board[j][i]) {
                                    board[index + 1][i] = block * 2;
                                    block = 0;
                                    board[j][i] = 0;
                                } else {
                                    block = board[j][i];
                                    board[j][i] = 0;
                                    board[index][i] = block;
                                    index--;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    // 좌로 이동
                    for (int i = 0; i < N; i++) {
                        int index = 0;
                        int block = 0;
                        for (int j = 0; j < N; j++) {
                            if (board[i][j] != 0) {
                                if (block == board[i][j]) {
                                    board[i][index - 1] = block * 2;
                                    block = 0;
                                    board[i][j] = 0;
                                } else {
                                    block = board[i][j];
                                    board[i][j] = 0;
                                    board[i][index] = block;
                                    index++;
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    // 우로 이동
                    for (int i = 0; i < N; i++) {
                        int index = N - 1;
                        int block = 0;
                        for (int j = N - 1; j >= 0; j--) {
                            if (board[i][j] != 0) {
                                if (block == board[i][j]) {
                                    board[i][index + 1] = block * 2;
                                    block = 0;
                                    board[i][j] = 0;
                                } else {
                                    block = board[i][j];
                                    board[i][j] = 0;
                                    board[i][index] = block;
                                    index--;
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }

            move(count + 1);

            for (int i = 0; i < N; i++)
                board[i] = copy[i].clone();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p12100/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        max = 0;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0);

        System.out.println(max);
    }
}

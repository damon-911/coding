package boj.simulation.p16234;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] MX = { -1, 0, 1, 0 };
    static final int[] MY = { 0, 1, 0, -1 };

    static int N, L, R;
    static int[][] board;
    static boolean[][] visited;
    static List<Node> moveList; // 인구 이동이 필요한 노드 저장

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void move(int sum) {
        int average = sum / moveList.size();
        for (Node node : moveList) {
            board[node.x][node.y] = average;
        }
    }

    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        moveList = new ArrayList<>();
        moveList.add(new Node(x, y));

        visited[x][y] = true;

        int sum = board[x][y];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + MX[i];
                int ty = cur.y + MY[i];

                if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty]) {
                    continue;
                }

                int diff = Math.abs(board[cur.x][cur.y] - board[tx][ty]);
                if (L <= diff && diff <= R) {
                    queue.offer(new Node(tx, ty));
                    moveList.add(new Node(tx, ty));
                    sum += board[tx][ty];
                    visited[tx][ty] = true;
                }
            }
        }
        return sum;
    }

    static int check() {
        int result = 0;

        while (true) { // 더 이상 인구 이동이 일어나지 않을 때까지 반복
            boolean isMove = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);

                        if (moveList.size() > 1) {
                            move(sum);
                            isMove = true;
                        }
                    }
                }
            }

            if (!isMove)
                return result;

            result++;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p16234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(check());
    }
}
package boj.bfs.p1697;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int result = Integer.MAX_VALUE;
    static boolean[] check = new boolean[100001];

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { N, 1 });
        check[N] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0)
                    next = cur[0] + 1;
                else if (i == 1)
                    next = cur[0] - 1;
                else
                    next = cur[0] * 2;

                if (next == K) {
                    System.out.println(cur[1]);
                    return;
                }

                if (next >= 0 && next < check.length && !check[next]) {
                    queue.add(new int[] { next, cur[1] + 1 });
                    check[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p1697/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K)
            System.out.println(0);
        else
            bfs();
    }
}

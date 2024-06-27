package boj.bfs.p5014;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D;

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { S, 0 });

        boolean[] visited = new boolean[F + 1];
        visited[S] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == G) {
                return cur[1];
            }

            if (cur[0] + U <= F && !visited[cur[0] + U]) {
                visited[cur[0] + U] = true;
                queue.add(new int[] { cur[0] + U, cur[1] + 1 });
            }

            if (cur[0] - D >= 1 && !visited[cur[0] - D]) {
                visited[cur[0] - D] = true;
                queue.add(new int[] { cur[0] - D, cur[1] + 1 });
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p5014/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int result = bfs();

        System.out.println(result != -1 ? result : "use the stairs");
    }
}

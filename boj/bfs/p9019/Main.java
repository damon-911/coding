package boj.bfs.p9019;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static void findCommand(int A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);

        boolean[] visited = new boolean[10000];
        visited[A] = true;

        String[] command = new String[10000];
        Arrays.fill(command, "");

        while (!queue.isEmpty() && !visited[B]) {
            int cur = queue.poll();

            int D = (2 * cur) % 10000;
            int S = cur == 0 ? 9999 : cur - 1;
            int L = (cur % 1000) * 10 + cur / 1000;
            int R = (cur % 10) * 1000 + cur / 10;

            if (!visited[D]) {
                queue.add(D);
                visited[D] = true;
                command[D] = command[cur] + "D";
            }

            if (!visited[S]) {
                queue.add(S);
                visited[S] = true;
                command[S] = command[cur] + "S";
            }

            if (!visited[L]) {
                queue.add(L);
                visited[L] = true;
                command[L] = command[cur] + "L";
            }

            if (!visited[R]) {
                queue.add(R);
                visited[R] = true;
                command[R] = command[cur] + "R";
            }
        }

        System.out.println(command[B]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p9019/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            findCommand(A, B);
        }
    }
}

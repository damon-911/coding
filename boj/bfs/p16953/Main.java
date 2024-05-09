package boj.bfs.p16953;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int A, B;

    static long bfs() {
        Queue<long[]> queue = new LinkedList<>();
        queue.add(new long[] { A, 0 });

        while (!queue.isEmpty()) {
            long[] cur = queue.poll();

            if (cur[0] > B) {
                continue;
            }

            if (cur[0] == B) {
                return cur[1] + 1;
            }

            queue.add(new long[] { cur[0] * 2, cur[1] + 1 });
            queue.add(new long[] { cur[0] * 10 + 1, cur[1] + 1 });
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p16953/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }
}

package boj.bfs.p13549;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] visited = new boolean[100_001];

    static class Person {
        int pos;
        int time;

        public Person(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    static int bfs() {
        int result = Integer.MAX_VALUE;

        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person(N, 0));

        while (!queue.isEmpty()) {
            Person cur = queue.poll();

            visited[cur.pos] = true;

            if (cur.pos == K) {
                result = Math.min(result, cur.time);
                continue;
            }

            if (cur.pos * 2 <= 100_000 && !visited[cur.pos * 2]) {
                queue.add(new Person(cur.pos * 2, cur.time));
            }

            if (cur.pos + 1 <= 100_000 && !visited[cur.pos + 1]) {
                queue.add(new Person(cur.pos + 1, cur.time + 1));
            }

            if (cur.pos - 1 >= 0 && !visited[cur.pos - 1]) {
                queue.add(new Person(cur.pos - 1, cur.time + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p13549/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }
}

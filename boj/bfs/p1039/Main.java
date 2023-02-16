package boj.bfs.p1039;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, M;
    static int[] visited;
    static Queue<Integer> queue;

    public static int swap(int now, int i, int j) {
        char[] list = String.valueOf(now).toCharArray();

        // 해당 자리의 숫자 교환
        char temp = list[i];
        list[i] = list[j];
        list[j] = temp;

        // 앞자리가 0이면 error
        if (list[0] == '0')
            return 0;

        return Integer.parseInt(new String(list));
    }

    static void bfs() {
        while (!queue.isEmpty() && K > 0) {
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                int now = queue.poll();

                for (int i = 0; i < M - 1; i++) {
                    for (int j = i + 1; j < M; j++) {
                        int next = swap(now, i, j);

                        // 앞자리가 0이거나 현재 depth에서 방문한 적이 있는 경우 queue에 추가하지 않음.
                        if (next != 0 && visited[next] != K) {
                            queue.offer(next);
                            visited[next] = K;
                        }
                    }
                }
            }

            K--;
        }

        if (queue.isEmpty()) {
            System.out.println("-1");
        }
        else {
            int answer = 0;
            for (int num : queue) {
                answer = Math.max(num, answer);
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/bfs/p1039/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = String.valueOf(N).length();
        K = Integer.parseInt(st.nextToken());

        visited = new int[1000001];

        queue = new LinkedList<>();
        queue.offer(N);

        bfs();
    }
}

package boj.data_structure.p1715;

import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p1715/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int result = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.add(num);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + b);
            result += a + b;
        }

        System.out.println(result);
    }
}

package boj.sorting.p11000;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Subject implements Comparable<Subject> {
        int start;
        int end;

        public Subject(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Subject s) {
            if (this.start == s.start) {
                return this.end - s.end;
            }
            return this.start - s.start;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p11000/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Subject> subjects = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            subjects.add(new Subject(start, end));
        }

        Collections.sort(subjects);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cur = 0;

        for (Subject subject : subjects) {
            cur = subject.end;

            if (!pq.isEmpty() && pq.peek() <= subject.start) {
                pq.poll();
            }

            pq.add(cur);
        }

        System.out.println(pq.size());
    }
}
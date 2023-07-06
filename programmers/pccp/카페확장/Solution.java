package programmers.pccp.카페확장;

import java.util.PriorityQueue;

public class Solution {

    static int answer;
    static PriorityQueue<Person> waitPQ, sleepPQ;

    static class Person implements Comparable<Person> {
        int inTime;
        int takeTime;

        public Person(int inTime, int takeTime) {
            this.inTime = inTime;
            this.takeTime = takeTime;
        }

        @Override
        public int compareTo(Person p) {
            return this.inTime - p.inTime;
        }
    }

    private static void run() {
        long time = -1;
        int run = 0;

        while (true) {
            if (run == 0 && waitPQ.isEmpty() && sleepPQ.isEmpty())
                break;

            time++;

            if (run > 0)
                run--;

            while (!sleepPQ.isEmpty()) {
                if (sleepPQ.peek().inTime == time) {
                    waitPQ.add(sleepPQ.poll());

                    if (run > 0)
                        answer = Math.max(answer, waitPQ.size() + 1);
                    else
                        answer = Math.max(answer, waitPQ.size());
                } else
                    break;
            }

            if (!waitPQ.isEmpty() && run == 0) {
                Person p = waitPQ.poll();
                run = p.takeTime;
            }
        }
    }

    public static int solution(int[] menu, int[] order, int k) {
        answer = 0;

        waitPQ = new PriorityQueue<>();
        sleepPQ = new PriorityQueue<>();

        for (int i = 0; i < order.length; i++) {
            sleepPQ.add(new Person(i * k, menu[order[i]]));
        }

        run();

        return answer;
    }

    public static void main(String[] args) {
        int[] menu = { 5, 12, 30 };
        int[] order = { 1, 2, 0, 1 };
        int k = 10;

        System.out.println(solution(menu, order, k));
    }
}
package programmers.data_structure.과제진행하기;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solution {

    static class Subject implements Comparable<Subject> {
        String name;
        int start;
        int remain;

        public Subject(String name, int start, int remain) {
            this.name = name;
            this.start = start;
            this.remain = remain;
        }

        @Override
        public int compareTo(Subject s) {
            return this.start - s.start;
        }
    }

    static int convertMin(String time) {
        String[] str = time.split(":");
        int min = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        return min;
    }

    public static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        Stack<Subject> stack = new Stack<>(); // 도중에 멈춘 과제들

        PriorityQueue<Subject> queue = new PriorityQueue<>((o1, o2) -> (o1.start - o2.start));

        for (String[] p : plans) {
            queue.add(new Subject(p[0], convertMin(p[1]), Integer.parseInt(p[2])));
        }

        Subject subject = queue.poll();
        int now = subject.start;
        int idx = 0;

        while (true) {
            if (!queue.isEmpty() && now + subject.remain > queue.peek().start) { // 과제 중지
                stack.push(new Subject(subject.name, subject.start, subject.remain - (queue.peek().start - now)));
                subject = queue.poll();
                now = subject.start;
            } else { // 과제 완료
                answer[idx++] = subject.name;
                now += subject.remain;

                if (!queue.isEmpty() && now == queue.peek().start) { // 새로 시작해야 하는 과제가 있다면 새로운 과제 시작
                    subject = queue.poll();
                } else if (!stack.isEmpty()) { // 멈춰둔 과제 다시 시작
                    subject = stack.pop();
                } else if (!queue.isEmpty()) { // 다음 차례의 과제 시작
                    subject = queue.poll();
                    now = subject.start;
                } else
                    break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] plans = {
                { "science", "12:40", "50" },
                { "music", "12:20", "40" },
                { "history", "14:00", "30" },
                { "computer", "12:30", "100" }
        };

        System.out.println(Arrays.toString(solution(plans)));
    }
}
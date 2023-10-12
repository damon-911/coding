package programmers.string.셔틀버스;

import java.util.PriorityQueue;

public class Solution {

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String table : timetable) {
            int time = Integer.parseInt(table.substring(0, 2)) * 60 + Integer.parseInt(table.substring(3));
            pq.offer(time);
        }

        int start_time = 9 * 60;
        int last_time = 0;
        int total = 0;

        for (int i = 0; i < n; i++) {
            total = 0;
            while (!pq.isEmpty()) {
                int current_time = pq.peek();
                if (current_time <= start_time && total < m) {
                    pq.poll();
                    total++;
                } else
                    break;
                last_time = current_time - 1;
            }
            start_time += t;
        }

        if (total < m)
            last_time = start_time - t;

        answer = String.format("%02d", last_time / 60) + ":" + String.format("%02d", last_time % 60);

        return answer;
    }

    public static void main(String[] args) {
        int n = 1;
        int t = 1;
        int m = 5;
        String[] timetable = { "08:00", "08:01", "08:02", "08:03" };

        System.out.println(solution(n, t, m, timetable));
    }
}
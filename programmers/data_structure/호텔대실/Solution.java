package programmers.data_structure.호텔대실;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static int solution(String[][] book_time) {
        int[][] bookTime = new int[book_time.length][2];

        for (int i = 0; i < bookTime.length; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));

            // 청소시간 10분 추가
            end += 10;

            // end가 60분 이상일 경우
            if (end % 100 >= 60)
                end += 40;

            bookTime[i][0] = start;
            bookTime[i][1] = end;
        }

        Arrays.sort(bookTime, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] time : bookTime) {
            if (pq.isEmpty()) {
                pq.offer(time[1]);
                continue;
            }

            if (pq.peek() <= time[0]) {
                pq.poll();
            }

            pq.offer(time[1]);
        }

        return pq.size();
    }

    public static void main(String[] args) {
        String[][] book_time = {
                { "15:00", "17:00" },
                { "16:40", "18:20" },
                { "14:20", "15:20" },
                { "14:10", "19:20" },
                { "18:20", "21:20" }
        };

        System.out.println(solution(book_time));
    }
}
package programmers.data_structure.기능개발;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            queue.add((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }

        int count = 1;
        int cur = queue.poll();

        while (!queue.isEmpty()) {
            if (cur >= queue.peek()) {
                count++;
                queue.poll();
            } else {
                list.add(count);
                count = 1;
                cur = queue.poll();
            }
        }
        list.add(count);

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = { 93, 30, 55 };
        int[] speeds = { 1, 30, 5 };

        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }
}
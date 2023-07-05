package programmers.data_structure.이중우선순위큐;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String[] temp = op.split(" ");

            if (temp[0].equals("I")) {
                pqMin.add(Integer.parseInt(temp[1]));
                pqMax.add(Integer.parseInt(temp[1]));
            } else {
                if (!pqMin.isEmpty() && !pqMax.isEmpty()) {
                    if (temp[1].equals("1")) {
                        int max = pqMax.poll();
                        pqMin.remove(max);
                    } else {
                        int min = pqMin.poll();
                        pqMax.remove(min);
                    }
                }
            }
        }

        if (!pqMax.isEmpty())
            answer[0] = pqMax.poll();

        if (!pqMin.isEmpty())
            answer[1] = pqMin.poll();

        return answer;
    }

    public static void main(String[] args) {
        String[] operations = { "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1" };

        System.out.println(Arrays.toString(solution(operations)));
    }
}
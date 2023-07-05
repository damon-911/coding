package programmers.data_structure.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int sum = 0;

        Queue<Integer> queue = new LinkedList<>();

        for (int t : truck_weights) {
            while (true) {
                if (queue.isEmpty()) {
                    queue.offer(t);
                    sum += t;
                    answer++;
                    break;
                } else if (queue.size() == bridge_length) {
                    sum -= queue.poll();
                } else {
                    if (sum + t > weight) {
                        queue.offer(0);
                        answer++;
                    } else {
                        queue.offer(t);
                        sum += t;
                        answer++;
                        break;
                    }
                }
            }
        }

        return answer + bridge_length;
    }

    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = { 7, 4, 5, 6 };

        System.out.println(solution(bridge_length, weight, truck_weights));
    }
}
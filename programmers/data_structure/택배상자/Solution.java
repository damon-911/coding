package programmers.data_structure.택배상자;

import java.util.Stack;

public class Solution {

    public static int solution(int[] order) {
        int answer = 0;

        int idx = 0;
        int curBox = 1;

        Stack<Integer> stack = new Stack<>();

        while (idx < order.length) {
            if (order[idx] > curBox) {
                stack.push(curBox);
                curBox++;
            } else if (order[idx] == curBox) {
                answer++;
                idx++;
                curBox++;
            } else {
                while (!stack.empty() && order[idx] == stack.peek()) {
                    stack.pop();
                    answer++;
                    idx++;
                }

                if (idx >= order.length || order[idx] < curBox)
                    break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] order = { 4, 3, 1, 2, 5 };

        System.out.println(solution(order));
    }
}
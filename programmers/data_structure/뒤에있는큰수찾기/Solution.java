package programmers.data_structure.뒤에있는큰수찾기;

import java.util.Arrays;
import java.util.Stack;

public class Solution {

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() > numbers[i]) {
                    answer[i] = stack.peek();
                    break;
                } else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                answer[i] = -1;
            }

            stack.push(numbers[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = { 2, 3, 3, 5 };

        System.out.println(Arrays.toString(solution(numbers)));
    }
}
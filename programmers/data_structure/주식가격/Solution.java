package programmers.data_structure.주식가격;

import java.util.Arrays;
import java.util.Stack;

public class Solution {

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) { // 끝까지 가격이 떨어지지 않은 주식 처리
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 2, 3 };

        System.out.println(Arrays.toString(solution(prices)));
    }
}
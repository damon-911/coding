package programmers.data_structure.햄버거만들기;

import java.util.Stack;

public class Solution {

    public static int solution(int[] ingredient) {
        int answer = 0;

        // 1 : 빵, 2 : 야채, 3 : 고기
        // 1-2-3-1 순으로 쌓여 있을때 한번에 pop
        Stack<Integer> stack = new Stack<>();

        for (int in : ingredient) {
            stack.push(in);

            if (stack.size() >= 4) {
                int size = stack.size();

                if (stack.get(size - 1) == 1
                        && stack.get(size - 2) == 3
                        && stack.get(size - 3) == 2
                        && stack.get(size - 4) == 1) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ingredient = { 2, 1, 1, 2, 3, 1, 2, 3, 1 };

        System.out.println(solution(ingredient));
    }
}
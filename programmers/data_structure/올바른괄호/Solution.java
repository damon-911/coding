package programmers.data_structure.올바른괄호;

import java.util.Stack;

public class Solution {

    public static boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push('(');
            else {
                if (stack.empty()) {
                    answer = false;
                    break;
                }

                stack.pop();
            }
        }

        if (!stack.empty())
            answer = false;

        return answer;
    }

    public static void main(String[] args) {
        String s = "()()";

        System.out.println(solution(s));
    }
}
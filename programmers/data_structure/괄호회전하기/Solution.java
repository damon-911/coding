package programmers.data_structure.괄호회전하기;

import java.util.Stack;

public class Solution {

    public static boolean isCorrect(String str) {
        boolean flag = true;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length() && flag; i++) {
            switch (str.charAt(i)) {
                case '(':
                    stack.push(str.charAt(i));
                    break;
                case '{':
                    stack.push(str.charAt(i));
                    break;
                case '[':
                    stack.push(str.charAt(i));
                    break;
                case ')':
                    if (stack.empty())
                        flag = false;
                    else if (stack.peek() == '(')
                        stack.pop();
                    else
                        flag = false;
                    break;
                case '}':
                    if (stack.empty())
                        flag = false;
                    else if (stack.peek() == '{')
                        stack.pop();
                    else
                        flag = false;
                    break;
                case ']':
                    if (stack.empty())
                        flag = false;
                    else if (stack.peek() == '[')
                        stack.pop();
                    else
                        flag = false;
                    break;
                default:
                    flag = false;
                    break;
            }
        }

        if (!stack.empty())
            flag = false;

        return flag;
    }

    public static int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, s.length()) + s.substring(0, i);
            if (isCorrect(temp))
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "[](){}";

        System.out.println(solution(s));
    }
}
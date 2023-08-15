package boj.data_structure.p2504;

import java.io.*;
import java.util.Stack;

public class Main {

    static int result = 0;

    static boolean check(String str) {
        Stack<Character> stack = new Stack<>();

        int value = 1; // () 카운트 (x2), [] 카운트 (x3)

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
                value *= 2;
            } else if (c == '[') {
                stack.push(c);
                value *= 3;
            } else if (c == ')') {
                if (stack.empty() || stack.peek() != '(')
                    return false;

                if (str.charAt(i - 1) == '(')
                    result += value;

                stack.pop();
                value /= 2;
            } else if (c == ']') {
                if (stack.empty() || stack.peek() != '[')
                    return false;

                if (str.charAt(i - 1) == '[')
                    result += value;

                stack.pop();
                value /= 3;
            }
        }

        if (stack.empty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p2504/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        if (!check(input))
            System.out.println(0);
        else {
            System.out.println(result);
        }
    }
}

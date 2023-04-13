package boj.data_structure.p4949;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p4949/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();

            if (str.equals("."))
                break;

            Stack<Character> stack = new Stack<>();

            boolean isCorrect = true;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '(' || c == '[') {
                    stack.push(c);
                }
                else if (c == ')') {
                    if (stack.empty() || stack.peek() != '(')
                        isCorrect = false;
                    else
                        stack.pop();
                }
                else if (c == ']') {
                    if (stack.empty() || stack.peek() != '[')
                        isCorrect = false;
                    else
                        stack.pop();
                }
            }

            if (str.charAt(str.length() - 1) != '.')
                isCorrect = false;

            if (!stack.empty())
                isCorrect = false;

            if (isCorrect)
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}

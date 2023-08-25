package boj.data_structure.p10828;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p10828/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            switch (command) {
                case "pop":
                    if (stack.empty())
                        System.out.println(-1);
                    else
                        System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    if (stack.empty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "top":
                    if (stack.empty())
                        System.out.println(-1);
                    else
                        System.out.println(stack.peek());
                    break;
                default:
                    String[] temp = command.split(" ");
                    stack.push(Integer.parseInt(temp[1]));
                    break;
            }
        }
    }
}

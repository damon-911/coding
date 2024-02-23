package boj.data_structure.p3425;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static final long MAX = 1000000000;

    static List<String[]> list = new ArrayList<>();
    static Stack<long[]> stack = new Stack<>();

    public static boolean check() {
        for (String[] cur : list) {
            if (cur[0].equals("NUM")) {
                stack.push(new long[] { Long.parseLong(cur[1]) });
            } else if (cur[0].equals("POP")) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            } else if (cur[0].equals("INV")) {
                if (stack.isEmpty()) {
                    return false;
                }
                long a = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { -a });
            } else if (cur[0].equals("DUP")) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.push(stack.peek());
            } else if (cur[0].equals("SWP")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num1 });
                stack.push(new long[] { num2 });
            } else if (cur[0].equals("ADD")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num1 + num2 });
            } else if (cur[0].equals("SUB")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num2 - num1 });
            } else if (cur[0].equals("MUL")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num1 * num2 });
            } else if (cur[0].equals("DIV")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                if (num1 == 0)
                    return false;
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num2 / num1 });
            } else if (cur[0].equals("MOD")) {
                if (stack.size() < 2) {
                    return false;
                }
                long num1 = stack.peek()[0];
                stack.pop();
                if (num1 == 0) {
                    return false;
                }
                long num2 = stack.peek()[0];
                stack.pop();
                stack.push(new long[] { num2 % num1 });
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p3425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while (true) {
            String command;
            while (true) {
                st = new StringTokenizer(br.readLine());

                if (!st.hasMoreTokens()) {
                    list.clear();
                    System.out.println();
                    continue;
                }

                command = st.nextToken();
                if (command.equals("NUM")) {
                    String num = st.nextToken();
                    list.add(new String[] { command, num });
                } else if (command.equals("END") || command.equals("QUIT")) {
                    break;
                } else {
                    list.add(new String[] { command });
                }
            }

            if (command.equals("QUIT")) {
                break;
            }

            int N = Integer.parseInt(br.readLine());
            while (N-- > 0) {
                long num = Long.parseLong(br.readLine());
                stack.push(new long[] { num });

                if (!check() || stack.size() != 1) {
                    System.out.println("ERROR");
                } else {
                    if (Math.abs(stack.peek()[0]) > MAX) {
                        System.out.println("ERROR");
                    } else {
                        System.out.println(stack.peek()[0]);
                    }
                }

                stack.clear();
            }
        }
    }
}

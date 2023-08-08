package boj.simulation.p5430;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static Deque<Integer> deque = new LinkedList<>();

    static String ac(String commands) {
        boolean reverse = false;

        for (char command : commands.toCharArray()) {
            if (command == 'R')
                reverse = !reverse;
            else {
                if (deque.size() == 0)
                    return "error";

                if (reverse)
                    deque.removeLast();
                else
                    deque.removeFirst();
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append('[');
        while (!deque.isEmpty()) {
            sb.append(reverse ? deque.removeLast() : deque.removeFirst());
            if (deque.size() != 0)
                sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p5430/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String commands = br.readLine();
            int count = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            if (count == 0) {
                if (commands.contains("D"))
                    System.out.println("error");
                else
                    System.out.println("[]");
            } else {
                String[] nums = arr.substring(1, arr.length() - 1).split(",");
                for (String num : nums) {
                    if (!num.equals(""))
                        deque.add(Integer.valueOf(num));
                }

                System.out.println(ac(commands));
            }
        }
    }
}

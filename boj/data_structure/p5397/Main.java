package boj.data_structure.p5397;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p5397/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            Stack<Character> cur = new Stack<>();
            Stack<Character> save = new Stack<>();

            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                switch (c) {
                    case '<':
                        if (!cur.isEmpty()) {
                            save.push(cur.pop());
                        }
                        break;
                    case '>':
                        if (!save.isEmpty()) {
                            cur.push(save.pop());
                        }
                        break;
                    case '-':
                        if (!cur.isEmpty()) {
                            cur.pop();
                        }
                        break;
                    default:
                        cur.push(c);
                        break;
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!cur.isEmpty()) {
                save.push(cur.pop());
            }
            while (!save.isEmpty()) {
                sb.append(save.pop());
            }
            System.out.println(sb.toString());
        }
    }
}

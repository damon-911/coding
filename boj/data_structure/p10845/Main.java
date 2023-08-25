package boj.data_structure.p10845;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p10845/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            switch (command) {
                case "pop":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.poll());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if (deque.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "front":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peekFirst());
                    break;
                case "back":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peekLast());
                    break;
                default:
                    String[] temp = command.split(" ");
                    deque.offer(Integer.parseInt(temp[1]));
                    break;
            }
        }
    }
}

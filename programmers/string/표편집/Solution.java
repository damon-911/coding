package programmers.string.표편집;

import java.util.Stack;

public class Solution {

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        Stack<Integer> removed = new Stack<>();

        int tableSize = n;

        for (String s : cmd) {
            String[] temp = s.split(" ");

            switch (temp[0]) {
                case "U":
                    k -= Integer.parseInt(temp[1]);
                    break;
                case "D":
                    k += Integer.parseInt(temp[1]);
                    break;
                case "C":
                    removed.add(k);
                    tableSize--;
                    if (k == tableSize) {
                        k--;
                    }
                    break;
                case "Z":
                    if (removed.pop() <= k) {
                        k++;
                    }
                    tableSize++;
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < tableSize; i++) {
            answer.append("O");
        }

        while (!removed.isEmpty()) {
            answer.insert(removed.pop().intValue(), "X");
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };

        System.out.println(solution(n, k, cmd));
    }
}
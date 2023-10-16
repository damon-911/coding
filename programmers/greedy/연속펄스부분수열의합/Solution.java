package programmers.greedy.연속펄스부분수열의합;

public class Solution {

    public static long solution(int[] sequence) {
        long answer = 0;

        boolean isPlus = true;

        long purseFirstPlus = 0;
        long purseFirstMinus = 0;

        for (int num : sequence) {
            purseFirstPlus += isPlus ? num : -num;
            purseFirstMinus += isPlus ? -num : num;

            purseFirstPlus = Math.max(0, purseFirstPlus);
            purseFirstMinus = Math.max(0, purseFirstMinus);

            answer = Math.max(answer, Math.max(purseFirstPlus, purseFirstMinus));

            isPlus = !isPlus;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] sequence = { 2, 3, -6, 1, 3, -1, 2, 4 };

        System.out.println(solution(sequence));
    }
}
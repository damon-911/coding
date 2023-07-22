package programmers.kakao2023.표현가능한이진트리;

import java.util.Arrays;

public class Solution {

    static boolean[] binary;
    static int result;

    private static void isPossible(int start, int end, boolean check) {
        int mid = (start + end) / 2;

        // 루트가 0이면 자식노드 중에서 1이 나오면 안됨
        if (check && binary[mid]) {
            result = 0;
            return;
        }

        // 자신이 마지막 노드가 아니면 재귀 수행
        if (start != end) {
            isPossible(start, mid - 1, !binary[mid]);
            isPossible(mid + 1, end, !binary[mid]);
        }
    }

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String num = Long.toBinaryString(numbers[i]);

            int treeH;
            int temp = 1;
            do {
                treeH = (int) Math.pow(2, temp++) - 1;
            } while (treeH < num.length());

            binary = new boolean[treeH];
            int idx = treeH - num.length();
            for (int j = 0; j < num.length(); j++) {
                binary[idx++] = (num.charAt(j) == '1');
            }

            result = 1;
            isPossible(0, treeH - 1, false);
            answer[i] = result;
        }

        return answer;
    }

    public static void main(String[] args) {
        long[] numbers = { 7, 42, 5 };

        System.out.println(Arrays.toString(solution(numbers)));
    }
}
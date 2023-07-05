package programmers.sorting.두개뽑아서더하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    static ArrayList<Integer> list = new ArrayList<>();;

    public static void comb(int[] numbers, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    sum += numbers[i];
                }
            }

            if (!list.contains(sum))
                list.add(sum);

            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            comb(numbers, visited, i + 1, n, r - 1);
            visited[i] = false;
        }

    }

    public static int[] solution(int[] numbers) {
        boolean[] visited = new boolean[numbers.length];

        comb(numbers, visited, 0, numbers.length, 2);

        Collections.sort(list);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = { 2, 1, 3, 4, 1 };

        System.out.println(Arrays.toString(solution(numbers)));
    }
}
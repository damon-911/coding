package programmers.recursive.쿼드압축후개수세기;

import java.util.Arrays;

public class Solution {

    static int[] answer;

    public static boolean check(int[][] arr, int size, int x, int y) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[x][y] != arr[i][j])
                    return false;
            }
        }

        return true;
    }

    public static void compress(int[][] arr, int size, int x, int y) {
        if (check(arr, size, x, y)) {
            answer[arr[x][y]]++;
            return;
        }

        compress(arr, size / 2, x, y);
        compress(arr, size / 2, x + size / 2, y);
        compress(arr, size / 2, x, y + size / 2);
        compress(arr, size / 2, x + size / 2, y + size / 2);
    }

    public static int[] solution(int[][] arr) {
        answer = new int[2];

        compress(arr, arr.length, 0, 0);

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr = {
                { 1, 1, 0, 0 },
                { 1, 0, 0, 0 },
                { 1, 0, 0, 1 },
                { 1, 1, 1, 1 }
        };

        System.out.println(Arrays.toString(solution(arr)));
    }
}
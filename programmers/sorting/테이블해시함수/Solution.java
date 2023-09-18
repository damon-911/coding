package programmers.sorting.테이블해시함수;

import java.util.Comparator;
import java.util.Arrays;

public class Solution {

    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] d1, int[] d2) {
                return d1[col - 1] != d2[col - 1] ? d1[col - 1] - d2[col - 1] : d2[0] - d1[0];
            }
        });

        for (int i = row_begin - 1; i <= row_end - 1; i++) {
            int temp = 0;
            for (int d : data[i]) {
                temp += d % (i + 1);
            }
            answer = (answer ^ temp);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] data = {
                { 2, 2, 6 },
                { 1, 5, 10 },
                { 4, 2, 9 },
                { 3, 8, 3 }
        };
        int col = 2;
        int row_begin = 2;
        int row_end = 3;

        System.out.println(solution(data, col, row_begin, row_end));
    }
}
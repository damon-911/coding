package programmers.bruteforce_search.최소직사각형;

import java.util.Arrays;

public class Solution {

    public static int solution(int[][] sizes) {
        int max_w = 0;
        int max_h = 0;

        for (int i = 0; i < sizes.length; i++) {
            Arrays.sort(sizes[i]);
        }

        for (int i = 0; i < sizes.length; i++) {
            if (max_w < sizes[i][0])
                max_w = sizes[i][0];

            if (max_h < sizes[i][1])
                max_h = sizes[i][1];
        }

        return max_w * max_h;
    }

    public static void main(String[] args) {
        int[][] sizes = {
                { 60, 50 },
                { 30, 70 },
                { 60, 30 },
                { 80, 40 }
        };

        System.out.println(solution(sizes));
    }
}
package programmers.greedy.단속카메라;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] == r2[1] ? r1[0] - r2[0] : r1[1] - r2[1];
            }
        });

        int cam = Integer.MIN_VALUE;

        for (int[] route : routes) {
            if (cam < route[0]) {
                cam = route[1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {
                { -20, -15 },
                { -14, -5 },
                { -18, -13 },
                { -5, -3 }
        };

        System.out.println(solution(routes));
    }
}
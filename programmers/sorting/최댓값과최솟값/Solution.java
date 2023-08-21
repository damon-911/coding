package programmers.sorting.최댓값과최솟값;

import java.util.Arrays;

public class Solution {

    public static String solution(String s) {
        String answer = "";

        int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);

        answer = nums[0] + " " + nums[nums.length - 1];

        return answer;
    }

    public static void main(String[] args) {
        String s = "1 2 3 4";

        System.out.println(solution(s));
    }
}
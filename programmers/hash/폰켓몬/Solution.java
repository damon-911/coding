package programmers.hash.폰켓몬;

import java.util.HashSet;

public class Solution {

    public static int solution(int[] nums) {
        int answer = 0;

        HashSet<Integer> hashSet = new HashSet<>();

        for (int num : nums)
            hashSet.add(num);

        answer = hashSet.size() <= nums.length / 2 ? hashSet.size() : nums.length / 2;

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 1, 2, 3 };

        System.out.println(solution(nums));
    }
}
package programmers.hash.연속부분수열합의개수;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static int solution(int[] elements) {
        Set<Integer> numSet = new HashSet<>();

        for (int count = 1; count <= elements.length; count++) {
            for (int i = 0; i < elements.length; i++) {
                int sum = 0;
                for (int j = i; j < i + count; j++) {
                    if (j >= elements.length)
                        sum += elements[j - elements.length];
                    else
                        sum += elements[j];
                }
                numSet.add(sum);
            }
        }

        return numSet.size();
    }

    public static void main(String[] args) {
        int[] elements = { 7, 9, 1, 1, 4 };

        System.out.println(solution(elements));
    }
}
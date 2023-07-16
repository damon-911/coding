package programmers.greedy.구명보트;

import java.util.Arrays;

public class Solution {

    public static int solution(int[] people, int limit) {
        int answer = 0;
        int index = 0;

        Arrays.sort(people);

        for (int i = people.length - 1; i >= index; i--) {
            if (people[index] + people[i] <= limit) {
                index++;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] people = { 70, 50, 80, 50 };
        int limit = 100;

        System.out.println(solution(people, limit));
    }
}

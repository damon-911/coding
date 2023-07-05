package programmers.hash.없는숫자더하기;

public class Solution {
    public static int solution(int[] numbers) {
        int answer = 0;

        boolean[] visited = new boolean[10];

        for (int num : numbers)
            visited[num] = true;

        for (int i = 0; i < 10; i++) {
            if (!visited[i])
                answer += i;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 6, 7, 8, 0 };

        System.out.println(solution(numbers));
    }
}
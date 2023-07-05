package programmers.recursive.모음사전;

public class Solution {

    public static int count(int num) {
        int result = 0;

        for (int i = 0; i <= num; i++)
            result += Math.pow(5, i);

        return result;
    }

    public static int solution(String word) {
        int answer = 0;

        char[] vowels = new char[] { 'A', 'E', 'I', 'O', 'U' };

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            for (int j = 0; j < 5; j++) {
                if (c == vowels[j])
                    answer += count(4 - i) * j;
            }

            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String word = "AAAAE";

        System.out.println(solution(word));
    }
}
package programmers.string.옹알이2;

public class Solution {

    static String[] words = { "aya", "ye", "woo", "ma" };

    public static int solution(String[] babbling) {
        int answer = 0;

        for (String str : babbling) {
            if (str.contains("ayaaya") || str.contains("yeye")
                    || str.contains("woowoo") || str.contains("mama")) {
                continue;
            }

            for (String word : words) {
                str = str.replaceAll(word, " ");
            }

            str = str.replaceAll(" ", "");
            if (str.equals(""))
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] babbling = { "aya", "yee", "u", "maa" };

        System.out.println(solution(babbling));
    }
}
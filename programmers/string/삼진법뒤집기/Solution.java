package programmers.string.삼진법뒤집기;

public class Solution {

    public static int solution(int n) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        int temp = n;

        while (temp > 0) {
            sb.append(String.valueOf(temp % 3));
            temp /= 3;
        }

        String str = sb.toString();

        for (int i = 0; i < str.length(); i++) {
            answer += Math.pow(3, i) * (str.charAt(str.length() - 1 - i) - '0');
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 45;

        System.out.println(solution(n));
    }
}
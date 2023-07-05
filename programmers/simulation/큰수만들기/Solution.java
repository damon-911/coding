package programmers.simulation.큰수만들기;

public class Solution {

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        int max = 0;

        for (int i = 0; i < number.length() - k; i++) {
            max = 0;
            for (int j = index; j <= i + k; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    index = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String number = "1924";
        int k = 2;

        System.out.println(solution(number, k));
    }
}
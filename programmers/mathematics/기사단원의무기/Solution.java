package programmers.mathematics.기사단원의무기;

public class Solution {

    static int findNum(int number, int limit, int power) {
        int count = 0;

        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (i * i == number)
                count++;
            else if (number % i == 0)
                count += 2;
        }

        return count > limit ? power : count;
    }

    public static int solution(int number, int limit, int power) {
        int answer = 0;

        for (int i = 1; i <= number; i++)
            answer += findNum(i, limit, power);

        return answer;
    }

    public static void main(String[] args) {
        int number = 5;
        int limit = 3;
        int power = 2;

        System.out.println(solution(number, limit, power));
    }
}
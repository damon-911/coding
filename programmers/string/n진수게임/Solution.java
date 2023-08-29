package programmers.string.n진수게임;

public class Solution {

    static String changeNum(int num, int n) {
        StringBuilder result = new StringBuilder();

        while (num > 0) {
            int temp = num % n;

            if (temp < 10)
                result.append(String.valueOf(temp));
            else
                result.append(String.valueOf((char) ('A' + (temp - 10))));

            num /= n;
        }

        return result.reverse().toString();
    }

    public static String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        int num = 0;
        int turn = 0;

        while (answer.length() < t) {
            if (num == 0) {
                if (turn == p - 1)
                    answer.append("0");
                turn = (turn + 1) % m;
            } else {
                String str = changeNum(num, n);
                for (int i = 0; i < str.length(); i++) {
                    if (turn == p - 1)
                        answer.append(str.charAt(i));
                    turn = (turn + 1) % m;

                    if (answer.length() == t)
                        break;
                }
            }
            num++;
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 2;

        System.out.println(solution(n, t, m, p));
    }
}
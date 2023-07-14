package programmers.dp.도둑질;

public class Solution {

    public static int solution(int[] money) {
        int answer = 0;

        if (money.length == 3) {
            for (int i = 0; i < 3; i++) {
                answer = Math.max(answer, money[i]);
            }
            return answer;
        }

        int[] dpGetFirst = new int[money.length];
        int[] dpIgnoreFirst = new int[money.length];

        // 첫 집을 무조건 털고 마지막 집은 무조건 털지 않는 경우
        dpGetFirst[0] = money[0];
        dpGetFirst[1] = Math.max(money[0], money[1]);

        // 첫 집을 무조건 털지 않는 경우
        dpIgnoreFirst[0] = 0;
        dpIgnoreFirst[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dpIgnoreFirst[i] = Math.max(dpIgnoreFirst[i - 1], dpIgnoreFirst[i - 2] + money[i]);
            answer = Math.max(answer, dpIgnoreFirst[i]);

            // dpGetFirst -> 마지막 집은 털지 않기 떄문에 넘어가야 한다
            if (i == money.length - 1)
                break;

            dpGetFirst[i] = Math.max(dpGetFirst[i - 1], dpGetFirst[i - 2] + money[i]);
            answer = Math.max(answer, dpGetFirst[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] money = { 1, 2, 3, 1 };

        System.out.println(solution(money));
    }
}
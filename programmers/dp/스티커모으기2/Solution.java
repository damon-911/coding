package programmers.dp.스티커모으기2;

public class Solution {

    public static int solution(int sticker[]) {
        int answer = 0;

        if (sticker.length <= 3) {
            for (int i = 0; i < sticker.length; i++) {
                answer = Math.max(answer, sticker[i]);
            }
            return answer;
        }

        int[] dpGetFirst = new int[sticker.length]; // 첫 번째 스티커를 뜯고 마지막 스티커는 뜯지 않는 경우
        dpGetFirst[0] = sticker[0];
        dpGetFirst[1] = Math.max(sticker[0], sticker[1]);

        int[] dpIgnoreFirst = new int[sticker.length]; // 첫 번째 스티커를 뜯지 않았을 경우
        dpIgnoreFirst[0] = 0;
        dpIgnoreFirst[1] = sticker[1];

        for (int i = 2; i < sticker.length; i++) {
            dpIgnoreFirst[i] = Math.max(dpIgnoreFirst[i - 1], dpIgnoreFirst[i - 2] + sticker[i]);
            answer = Math.max(answer, dpIgnoreFirst[i]);

            // dpGetFirst -> 마지막 스티커는 뜯지 않기 떄문에 패스
            if (i == sticker.length - 1)
                break;

            dpGetFirst[i] = Math.max(dpGetFirst[i - 1], dpGetFirst[i - 2] + sticker[i]);
            answer = Math.max(answer, dpGetFirst[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] sticker = { 14, 6, 5, 11, 3, 9, 2, 10 };

        System.out.println(solution(sticker));
    }
}
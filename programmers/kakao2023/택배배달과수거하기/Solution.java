package programmers.kakao2023.택배배달과수거하기;

public class Solution {

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dNum = 0;
        int pNum = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0)
                continue;

            int count = 0;

            // 현재 인덱스 위치를 몇 번 방문할지 카운트
            while (dNum < deliveries[i] || pNum < pickups[i]) {
                count++;
                dNum += cap;
                pNum += cap;
            }

            // 남은 트럭 자리 계산
            dNum -= deliveries[i];
            pNum -= pickups[i];

            answer += (i + 1) * count * 2;
        }

        return answer;
    }

    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = { 1, 0, 3, 1, 2 };
        int[] pickups = { 0, 3, 0, 4, 0 };

        System.out.println(solution(cap, n, deliveries, pickups));
    }
}
package programmers.array.스타수열;

public class Solution {

    public static int solution(int[] a) {
        int answer = 0;

        int[] count = new int[a.length];
        for (int num : a) { // num 원소 등장 횟수 저장
            count[num]++;
        }

        for (int i = 0; i < a.length; i++) { // 공통 원소 선택
            if (count[i] == 0 || count[i] <= answer)
                continue;

            int len = 0;
            for (int j = 0; j < a.length - 1; j++) {
                // 인접한 2개의 값이 어느 하나라도 공통된 원소를 가지고 있는지 확인
                if (a[j] != i && a[j + 1] != i)
                    continue;

                // 인접한 2개의 값이 동일하지는 않은지 확인
                if (a[j] == a[j + 1])
                    continue;

                len++;
                j++;
            }
            answer = Math.max(answer, len);
        }

        return answer * 2;
    }

    public static void main(String[] args) {
        int[] a = { 5, 2, 3, 3, 5, 3 };

        System.out.println(solution(a));
    }
}
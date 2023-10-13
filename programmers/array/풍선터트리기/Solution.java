package programmers.array.풍선터트리기;

public class Solution {

    public static int solution(int[] a) {
        int[] leftMin = new int[a.length]; // 맨 왼쪽 인덱스부터 최솟값을 저장
        int[] rightMin = new int[a.length]; // 맨 오른쪽 인덱스부터 최솟값을 저장

        int left = a[0];
        int right = a[a.length - 1];

        for (int i = 1; i < a.length - 1; i++) {
            left = Math.min(left, a[i]);
            leftMin[i] = left;
        }

        for (int i = a.length - 2; i > 0; i--) {
            right = Math.min(right, a[i]);
            rightMin[i] = right;
        }

        if (a.length == 1)
            return 1;

        int answer = 2; // 원소가 2개 이상일 경우, 무조건 2개 이상은 남음
        for (int i = 1; i <= a.length - 2; i++) {
            if (a[i] > leftMin[i] && a[i] > rightMin[i])
                continue;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = { 9, -1, -5 };

        System.out.println(solution(a));
    }
}
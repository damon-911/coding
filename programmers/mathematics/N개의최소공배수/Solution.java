package programmers.mathematics.N개의최소공배수;

public class Solution {

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static int solution(int[] arr) {
        int answer = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int GCD = gcd(answer, arr[i]);
            answer *= arr[i] / GCD;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 6, 8, 14 };

        System.out.println(solution(arr));
    }
}
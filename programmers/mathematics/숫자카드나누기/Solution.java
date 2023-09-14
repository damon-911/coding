package programmers.mathematics.숫자카드나누기;

public class Solution {

    static boolean canDivide(int[] arr, int num) {
        for (int n : arr)
            if (n % num == 0)
                return true;
        return false;
    }

    static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        if (!canDivide(arrayB, gcdA))
            if (answer < gcdA)
                answer = gcdA;

        if (!canDivide(arrayA, gcdB))
            if (answer < gcdB)
                answer = gcdB;

        return answer;
    }

    public static void main(String[] args) {
        int[] arrayA = { 10, 20 };
        int[] arrayB = { 5, 17 };

        System.out.println(solution(arrayA, arrayB));
    }
}
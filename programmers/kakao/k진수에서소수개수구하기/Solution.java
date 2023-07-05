package programmers.kakao.k진수에서소수개수구하기;

public class Solution {

    private static boolean isPrime(long num) {
        if (num < 2)
            return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    private static String convert(int n, int k) {
        String temp = "";

        while (n != 0) {
            temp = n % k + temp;
            n /= k;
        }

        return temp;
    }

    public static int solution(int n, int k) {
        int answer = 0;

        String converted = convert(n, k);
        String[] primes = converted.split("0");

        for (String prime : primes) {
            if (prime.equals(""))
                continue;

            if (isPrime(Long.parseLong(prime)))
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 437674;
        int k = 3;

        System.out.println(solution(n, k));
    }
}
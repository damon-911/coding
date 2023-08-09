package programmers.mathematics.소수찾기;

public class Solution {

    public static int solution(int n) {
        int answer = 0;

        boolean[] isNotPrime = new boolean[n + 1];

        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i])
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 10;

        System.out.println(solution(n));
    }
}
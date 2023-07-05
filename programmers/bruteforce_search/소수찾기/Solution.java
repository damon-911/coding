package programmers.bruteforce_search.소수찾기;

import java.util.ArrayList;

public class Solution {

    static int maxNum = -1;
    static boolean[] prime;
    static ArrayList<Integer> numList = new ArrayList<>();

    public static void findPrime(int num) {
        prime = new boolean[num + 1];
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= num; i++) {
            // i가 소수라면
            if (!prime[i]) {
                // i의 배수는 모두 소수는 아니다
                for (int j = i * 2; j <= num; j += i) {
                    prime[j] = true;
                }
            }
        }
    }

    public static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            if (output[0] != 0) {
                String str = "";
                for (int i = 0; i < r; i++)
                    str += output[i];

                if (!numList.contains(Integer.parseInt(str)))
                    numList.add(Integer.parseInt(str));

                maxNum = Math.max(maxNum, Integer.parseInt(str));

                return;
            }

            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void getMax(String str) {
        int[] nums = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            nums[i] = str.charAt(i) - '0';
        }

        int[] output = new int[nums.length];
        boolean[] visited = new boolean[nums.length];

        for (int i = 1; i <= nums.length; i++)
            perm(nums, output, visited, 0, nums.length, i);
    }

    public static int solution(String numbers) {
        int answer = 0;

        getMax(numbers);

        findPrime(maxNum);

        for (int i = 0; i < numList.size(); i++) {
            if (!prime[numList.get(i)])
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String numbers = "17";

        System.out.println(solution(numbers));
    }
}
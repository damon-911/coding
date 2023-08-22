package programmers.binary_search.숫자의표현;

public class Solution {

    static boolean binarySearch(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int solution(int n) {
        int answer = 0;

        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + i;
        }

        for (int i = 1; i <= n; i++) {
            int target = sum[i] - n;
            if (target >= 0 && binarySearch(sum, 0, n, target)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 15;

        System.out.println(solution(n));
    }
}
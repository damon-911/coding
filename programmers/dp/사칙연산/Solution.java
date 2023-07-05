package programmers.dp.사칙연산;

public class Solution {

    private static int[] num;
    private static String[] oper;
    private static int[][][] dp;

    private static boolean isVisited(int flag, int start, int end) {
        // 현재값이 초기값과 동일하지 않으면 이미 방문한 경우임
        // flag = 0일 때는 초기값이 Integer.MIN_VALUE;
        // flag = 1일 때는 초기값이 Integer.MAX_VALUE;
        if (flag == 0)
            return dp[flag][start][end] != Integer.MIN_VALUE;
        else
            return dp[flag][start][end] != Integer.MAX_VALUE;
    }

    private static int calculate(int flag, int start, int end) {
        // start == end인 경우는 숫자 하나만 선택된 경우이므로 자기 자신을 리턴
        if (start == end) {
            dp[flag][start][end] = num[start];
            return dp[flag][start][end];
        }

        // 이미 계산했었던 경우, 기존에 계산된 값 사용
        if (isVisited(flag, start, end)) {
            return dp[flag][start][end];
        }

        // 방문 체크
        dp[flag][start][end] = 0;

        int result = (flag == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // 최댓값을 구해야할 때 flag = 0
        if (flag == 0) {
            for (int mid = start; mid < end; mid++) {
                if (oper[mid].equals("-")) {
                    // a - b가 최댓값이 되는 조건 -> a는 최댓값, b는 최솟값
                    result = Math.max(result, calculate(0, start, mid) - calculate(1, mid + 1, end));
                    continue;
                }

                // a + b가 최댓값이 되는 조건 -> a, b 둘 다 최댓값
                result = Math.max(result, calculate(0, start, mid) + calculate(0, mid + 1, end));
            }
        } else { // 최솟값을 구해야할 때 flag = 1
            for (int mid = start; mid < end; mid++) {
                if (oper[mid].equals("-")) {
                    // -(a - b)가 최댓값이 되는 조건 -> a는 최솟값, b는 최댓값
                    result = Math.min(result, calculate(1, start, mid) - calculate(0, mid + 1, end));
                    continue;
                }

                // -(a + b)가 최댓값이 되는 조건 -> a, b, 둘 다 최솟값
                result = Math.min(result, calculate(1, start, mid) + calculate(1, mid + 1, end));
            }
        }

        dp[flag][start][end] = result;

        return dp[flag][start][end];
    }

    public static int solution(String arr[]) {
        int answer = -1;

        int n = arr.length / 2;

        num = new int[n + 1];
        oper = new String[n];

        dp = new int[2][200][200];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                // dp[0]은 최댓값, dp[1]은 최솟값
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                num[i / 2] = Integer.parseInt(arr[i]);
                continue;
            }
            oper[i / 2] = arr[i];
        }

        answer = calculate(0, 0, n);

        return answer;
    }

    public static void main(String[] args) {
        String[] arr = { "1", "-", "3", "+", "5", "-", "8" };

        System.out.println(solution(arr));
    }
}
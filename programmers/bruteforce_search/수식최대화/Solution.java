package programmers.bruteforce_search.수식최대화;

import java.util.ArrayList;

public class Solution {

    public static long answer = Long.MIN_VALUE;

    public static ArrayList<Long> numList = new ArrayList<>();
    public static ArrayList<String> opList = new ArrayList<>();

    public static String[] op = { "+", "-", "*" };
    public static String[] permResult = new String[3];
    public static boolean[] visited = new boolean[3];

    public static long cal(long a, long b, String op) {
        long result = 0;

        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
        }

        return result;
    }

    public static void solve() {
        ArrayList<Long> copyNumList = new ArrayList<Long>();
        copyNumList.addAll(numList);

        ArrayList<String> copyOpList = new ArrayList<String>();
        copyOpList.addAll(opList);

        for (int i = 0; i < permResult.length; i++) {
            // 현재 우선순위 연산자
            String cur = permResult[i];

            for (int j = 0; j < copyOpList.size(); j++) {
                if (copyOpList.get(j).equals(cur)) {
                    long a = copyNumList.get(j);
                    long b = copyNumList.get(j + 1);
                    long result = cal(a, b, cur);

                    copyNumList.remove(j + 1);
                    copyNumList.remove(j);
                    copyOpList.remove(j);

                    // 연산 결과 넣기
                    copyNumList.add(j, result);

                    // 삭제했으니 인덱스 하나 줄여주기
                    j--;
                }
            }
        }

        answer = Math.max(answer, Math.abs(copyNumList.get(0)));
    }

    public static void perm(int depth, int r) {
        if (depth == r) {
            solve();
            return;
        }

        for (int i = 0; i < op.length; i++) {
            if (!visited[i]) {
                visited[i] = true;

                permResult[depth] = op[i];
                perm(depth + 1, r);

                visited[i] = false;
            }
        }
    }

    public static long solution(String expression) {
        String str = "";

        for (int i = 0; i < expression.length(); i++) {
            char exp = expression.charAt(i);

            if (exp == '+' || exp == '-' || exp == '*') {
                opList.add(exp + "");
                numList.add(Long.parseLong(str));
                str = "";
            } else {
                str += exp;
            }
        }

        // 마지막 숫자 삽입
        numList.add(Long.parseLong(str));

        // 순열 구하기
        perm(0, op.length);

        return answer;
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";

        System.out.println(solution(expression));
    }
}
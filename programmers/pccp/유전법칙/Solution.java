package programmers.pccp.유전법칙;

import java.util.Arrays;

public class Solution {

    static String[] RrChild = { "RR", "Rr", "Rr", "rr" };
    static String result = "";

    private static void find(int depth, int n, int p, String cur) {
        if (depth == n) {
            if (cur.equals("Rr"))
                result = RrChild[p];
            else
                result = cur;
            return;
        }

        int q = p / (int) Math.pow(4, n - depth);
        int r = p % (int) Math.pow(4, n - depth);
        String temp = RrChild[q];

        if (temp.equals("RR")) {
            result = "RR";
            return;
        } else if (temp.equals("rr")) {
            result = "rr";
            return;
        } else
            find(depth + 1, n, r, "Rr");
    }

    public static String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int n = queries[i][0];
            int p = queries[i][1];

            if (n == 1)
                answer[i] = "Rr";
            else {
                find(2, n, p - 1, "Rr");
                answer[i] = result;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] queries = {
                { 3, 5 }
        };

        System.out.println(Arrays.toString(solution(queries)));
    }
}
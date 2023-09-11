package programmers.data_structure.괄호변환;

import java.util.Stack;

public class Solution {

    public static boolean isCorrect(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                st.push('(');
            } else {
                if (st.isEmpty() || st.peek() == ')') {
                    return false;
                } else {
                    st.pop();
                }
            }
        }

        return true;
    }

    static String divide(String str) {
        if (str.length() == 0) {
            return "";
        }

        String u = "";
        String v = "";
        int leftIdx = 0;
        int rightIdx = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                leftIdx++;
            else
                rightIdx++;

            u += str.charAt(i);

            if (leftIdx == rightIdx) {
                v = str.substring(i + 1);
                break;
            }
        }

        // 문쟈열 u가 올바른 괄호 문자열이라면 문자열 v에 대해 처음부터 다시 수행
        if (isCorrect(u)) {
            return u += divide(v);
        } else {
            String temp = "(";
            temp += divide(v);
            temp += ")";

            u = u.substring(1, u.length() - 1);

            for (int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == '(') {
                    temp += ')';
                } else {
                    temp += '(';
                }
            }

            return temp;
        }
    }

    public static String solution(String p) {
        String answer = divide(p);

        return answer;
    }

    public static void main(String[] args) {
        String p = "()))((()";

        System.out.println(solution(p));
    }
}
package programmers.string.문자열압축;

public class Solution {

    public static int solution(String s) {
        int answer = s.length();

        for (int count = 1; count <= s.length() / 2; count++) {
            String[] temp = s.split("(?<=\\G.{" + count + "})");

            StringBuilder sb = new StringBuilder();
            String cur = temp[0];
            int num = 1;

            for (int i = 1; i < temp.length; i++) {
                if (cur.equals(temp[i])) {
                    num++;
                } else {
                    if (num >= 2)
                        sb.append(num);
                    sb.append(cur);

                    num = 1;
                    cur = temp[i];
                }
            }

            if (num >= 2)
                sb.append(num);
            sb.append(cur);

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }

    public static void main(String[] args) {
        String s = "aabbaccc";

        System.out.println(solution(s));
    }
}
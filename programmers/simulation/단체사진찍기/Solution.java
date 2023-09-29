package programmers.simulation.단체사진찍기;

public class Solution {

    final static char[] friends = new char[] { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };

    static int answer;
    static boolean[] visited;

    static boolean check(String line, String[] data) {
        for (String d : data) {
            int diff = Math.abs(line.indexOf(d.charAt(0)) - line.indexOf(d.charAt(2))) - 1;
            char sign = d.charAt(3);
            int value = d.charAt(4) - '0';

            if (sign == '=') {
                if (diff != value)
                    return false;
            } else if (sign == '>') {
                if (diff <= value)
                    return false;
            } else {
                if (diff >= value)
                    return false;
            }
        }

        return true;
    }

    static void dfs(String line, int depth, String[] data) {
        if (depth == 8) {
            if (check(line, data))
                answer++;
            return;
        }

        for (int i = 0; i < 8; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(line + friends[i], depth + 1, data);
                visited[i] = false;
            }
        }
    }

    public static int solution(int n, String[] data) {
        answer = 0;

        visited = new boolean[8];

        dfs("", 0, data);

        return answer;
    }

    public static void main(String[] args) {
        int n = 2;
        String[] data = { "N~F=0", "R~T>2" };

        System.out.println(solution(n, data));
    }
}
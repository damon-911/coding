package programmers.simulation.단어변환;

import java.util.ArrayList;

public class Solution {

    static int answer = 0;
    static boolean[] visited;

    private static void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }

            int count = 0; // 같은 스펠링 몇개인지 세기
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    count++;
                }
            }

            if (count == begin.length() - 1) { // 한글자 빼고 모두 같은 경우
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        ArrayList<String> list = new ArrayList<>();
        for (String word : words)
            list.add(word);

        if (list.contains(target)) {
            dfs(begin, target, words, 0);
        } else {
            answer = 0;
        }

        return answer;
    }

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

        System.out.println(solution(begin, target, words));
    }
}
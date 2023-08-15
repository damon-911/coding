package programmers.array.대충만든자판;

import java.util.Arrays;

public class Solution {

    public static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        int[] key = new int[26];
        Arrays.fill(key, Integer.MAX_VALUE);

        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                char c = keymap[i].charAt(j);
                key[c - 'A'] = Math.min(key[c - 'A'], j + 1);
            }
        }

        for (int i = 0; i < targets.length; i++) {
            for (int j = 0; j < targets[i].length(); j++) {
                char c = targets[i].charAt(j);
                if (key[c - 'A'] == Integer.MAX_VALUE) {
                    answer[i] = -1;
                    break;
                }
                answer[i] += key[c - 'A'];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] keymap = { "ABACD", "BCEFD" };
        String[] targets = { "ABCD", "AABB" };

        System.out.println(Arrays.toString(solution(keymap, targets)));
    }
}
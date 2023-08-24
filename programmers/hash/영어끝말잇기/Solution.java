package programmers.hash.영어끝말잇기;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static int[] solution(int n, String[] words) {
        Set<String> hashSet = new HashSet<>();

        String lastWord = "";

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (!lastWord.equals("")
                    && lastWord.charAt(lastWord.length() - 1) != word.charAt(0)
                    || hashSet.contains(word)) {
                int num = i % n + 1;
                int turn = i / n + 1;
                return new int[] { num, turn };
            } else {
                lastWord = word;
                hashSet.add(word);
            }
        }

        return new int[] { 0, 0 };
    }

    public static void main(String[] args) {
        int n = 3;
        String[] words = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };

        System.out.println(Arrays.toString(solution(n, words)));
    }
}
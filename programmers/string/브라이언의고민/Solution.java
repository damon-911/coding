package programmers.string.브라이언의고민;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    static final String INVALID = "invalid";

    static String answer;
    static Map<Character, List<Integer>> lowerMap;

    static class Word {
        int rule;
        int start;
        int end;

        public Word(int rule, int start, int end) {
            this.rule = rule;
            this.start = start;
            this.end = end;
        }
    }

    static String getStr(int start, int end, String sentence) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            char c = sentence.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(c);
            }
        }
        return sb.toString() + " ";
    }

    static void checkLower(String sentence) {
        List<Word> words = new ArrayList<>();

        int startStr = 0;
        int rule = -1;

        int startChar = 0;
        int endChar = 0;
        int startPreChar = -1;
        int endPreChar = -1;

        int startWord = 0;
        int endWord = 0;
        int endPreWord = -1;

        for (List<Integer> positions : lowerMap.values()) {
            int num = positions.size();
            boolean isDistance2 = true;

            startWord = startChar = positions.get(0);
            endWord = endChar = positions.get(num - 1);

            for (int i = 1; i < num; i++) {
                int distance = positions.get(i) - positions.get(i - 1);
                if (distance < 2) { // 한 기호가 연속으로 붙어있는 경우
                    answer = INVALID;
                    return;
                } else if (distance > 2) {
                    isDistance2 = false;
                    break;
                }
            }

            if (num >= 3 && !isDistance2) { // 규칙1 - 간격이 2가 아닌 경우
                answer = INVALID;
                return;
            }

            if (num == 1 || num >= 3) {
                rule = 1;
                startWord--;
                endWord++;
                if (startWord < 0 || endWord >= sentence.length()) { // 규칙1 - 맨 앞/뒤 글자인 경우
                    answer = INVALID;
                    return;
                }
            } else if (num == 2) {
                rule = isDistance2 ? 0 : 2;
            }

            if (startPreChar < startChar && endChar < endPreChar) {
                if (rule == 2) { // 규칙2 - 한 단어에 규칙2 두 번 적용한 경우
                    answer = INVALID;
                    return;
                }
                continue; // 규칙2 안에 규칙1 존재
            }

            if (endPreWord >= startWord) { // 단어 범위 오류
                answer = INVALID;
                return;
            }

            words.add(new Word(rule, startWord, endWord));

            startPreChar = startChar;
            endPreChar = endChar;
            endPreWord = endWord;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            rule = word.rule;
            startWord = word.start;
            endWord = word.end;

            if (rule == 0) { // 규칙1 또는 규칙2
                // 규칙1
                if ((startStr <= startWord - 1)
                        && (endWord + 1 < (i < words.size() - 1 ? words.get(i + 1).start : sentence.length()))) {
                    startWord--;
                    endWord++;
                }
            }

            if (startStr < startWord) {
                sb.append(getStr(startStr, startWord - 1, sentence));
            }
            sb.append(getStr(startWord, endWord, sentence));
            startStr = endWord + 1;
        }

        if (startStr < sentence.length()) {
            sb.append(getStr(startStr, sentence.length() - 1, sentence));
        }

        answer = sb.toString().trim();
    }

    public static String solution(String sentence) {
        if (sentence.contains(" "))
            return INVALID;

        char[] strArr = sentence.toCharArray();

        lowerMap = new LinkedHashMap<>(); // 데이터를 넣은 순서대로 탐색 가능
        for (int i = 0; i < strArr.length; i++) {
            char c = strArr[i];
            if (Character.isLowerCase(c)) {
                if (!lowerMap.containsKey(c)) {
                    lowerMap.put(c, new ArrayList<>());
                }
                lowerMap.get(c).add(i);
            }
        }

        checkLower(sentence);

        return answer;
    }

    public static void main(String[] args) {
        String sentence = "HaEaLaLaObWORLDb";

        System.out.println(solution(sentence));
    }
}
package programmers.data_structure.가사검색;

import java.util.Arrays;

public class Solution {

    static TrieNode[] root = new TrieNode[10000];
    static TrieNode[] rootReverse = new TrieNode[10000];

    static class TrieNode {
        TrieNode[] child;
        int count;

        public TrieNode() {
            this.child = new TrieNode[26];
            this.count = 0;
        }

        // 해당 c가 자식으로 있는지 확인
        boolean hasChild(char c) {
            return child[c - 'a'] != null;
        }

        // 해당 c를 나타내는 자식 리턴
        TrieNode getChild(char c) {
            return child[c - 'a'];
        }

        // 문자 삽입
        void insert(String str) {
            TrieNode current = this;

            for (char ch : str.toCharArray()) {
                current.count++;
                // 해당 알파벳을 사전에 처음 등재할 경우
                if (!current.hasChild(ch)) {
                    current.child[ch - 'a'] = new TrieNode();
                }
                // 단어를 잇는 과정
                current = current.getChild(ch);
            }

            current.count++;
        }

        // 문자 검색
        int search(String str) {
            TrieNode current = this;

            for (char ch : str.toCharArray()) {
                if (ch == '?')
                    return current.count;

                current = current.getChild(ch);
                if (current == null)
                    return 0;
            }

            return current.count;
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int ansIdx = 0;

        for (String word : words) {
            int idx = word.length() - 1;

            if (root[idx] == null) {
                root[idx] = new TrieNode();
                rootReverse[idx] = new TrieNode();
            }

            root[idx].insert(word);

            String str = new StringBuilder(word).reverse().toString();
            rootReverse[idx].insert(str);
        }

        for (String query : queries) {
            int idx = query.length() - 1;

            if (root[idx] == null) {
                answer[ansIdx++] = 0;
                continue;
            }

            if (query.charAt(0) != '?') {
                answer[ansIdx++] = root[idx].search(query);
            } else {
                String str = new StringBuilder(query).reverse().toString();
                answer[ansIdx++] = rootReverse[idx].search(str);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
        String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?" };

        System.out.println(Arrays.toString(solution(words, queries)));
    }
}
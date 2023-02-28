package boj.data_structure.p9202;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] mx = {-1, 1, 0, 0, -1, -1, -1, 1};
    static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};

    static int W, B;
    static String[] words;
    static int[] scores;
    static char[][] board;
    static boolean[][] visited;

    static int sum;
    static String answer;
    static int count;

    static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
        boolean isHit;

        boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }

        TrieNode getChild(char c) {
            return child[c - 'A'];
        }

        void clearHit() {
            this.isHit = false;
            for (TrieNode trieNode : child) {
                if (trieNode != null)
                    trieNode.clearHit();
            }
        }
    }

    static void search(int y, int x, TrieNode node) {
        // 1. 체크인
        visited[y][x] = true;
        sb.append(board[y][x]);

        // 2. 목적지에 도달하였는가?
        if (node.isEnd && !node.isHit) {
            node.isHit = true;
            sum += scores[sb.length()];
            count++;

            String foundAnswer = sb.toString();
            if (compare(answer, foundAnswer) > 0)
                answer = foundAnswer;
        }

        // 3. 연결된 곳을 순회
        for (int i = 0; i < 8; i++) {
            int ty = y + my[i];
            int tx = x + mx[i];

            // 4. 가능한가? (맵을 벗어나지 않고, Trie에 단어가 있고, 방문한적이 없고)
            if ( 0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
                if (node.hasChild(board[ty][tx]) && !visited[ty][tx]) {
                    // 5. 간다
                    search(ty, tx, node.getChild(board[ty][tx]));
                }
            }
        }

        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    static int compare(String s1, String s2) {
        int result = s2.length() - s1.length();
        if (result == 0)
            return s1.compareTo(s2);

        return result;
    }

    static void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.hasChild(c)) {
                current.child[c - 'A'] = new TrieNode();
            }
            current = current.getChild(c);
        }

        current.isEnd = true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());

        words = new String[W];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
            insert(words[i]);
        }

        scores = new int[9];
        scores[0] = 0; scores[1] = 0; scores[2] = 0;
        scores[3] = 1; scores[4] = 1;
        scores[5] = 2; scores[6] = 3; scores[7] = 5; scores[8] = 11;

        br.readLine();

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        for (int num = 0; num < B; num++) {
            sum = 0;
            answer = "";
            count = 0;

            board = new char[4][4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = temp.charAt(j);
                }
            }

            visited = new boolean[4][4];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    search(i, j, root);
                }
            }

            System.out.println(sum + " " + answer + " " + count);

            root.clearHit();

            if (count != B - 1)
                br.readLine();
        }
    }
}

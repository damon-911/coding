package boj.data_structure.p9202;

import java.io.*;

public class Main {

    static int[] MX = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] MY = {0, 0, -1, 1, -1, -1, 1, 1};

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
        TrieNode[] child;
        boolean isEnd;
        boolean isHit;

        public TrieNode() {
            this.child = new TrieNode[26];
            this.isEnd = false;
            this.isHit = false;
        }

        // 해당 c가 자식으로 있는지 확인
        public boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }

        // 해당 c를 나타내는 자식 리턴
        public TrieNode getChild(char c) {
            return child[c - 'A'];
        }

        // root 초기화할 때 사용
        public void clearHit() {
            this.isHit = false;
            for (TrieNode trieNode : child) {
                if (trieNode != null)
                    trieNode.clearHit();
            }
        }
    }

    static int compare(String s1, String s2) {
        int result = s2.length() - s1.length();
        if (result == 0)
            return s1.compareTo(s2);

        return result;
    }

    static void search(int x, int y, TrieNode node) {
        // 1. 체크인
        visited[x][y] = true;
        sb.append(board[x][y]);

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
            int tx = x + MX[i];
            int ty = y + MY[i];

            // 4. 갈 수 있는가? (맵을 벗어나지 않고, 트라이에 단어가 있고, 방문한 적이 없고)
            if ( 0 <= tx && tx < 4 && 0 <= ty && ty < 4) {
                if (node.hasChild(board[tx][ty]) && !visited[tx][ty]) {
                    // 5. 간다
                    search(tx, ty, node.getChild(board[tx][ty]));
                }
            }
        }

        // 6. 체크아웃
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    static void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 해당 알파벳을 사전에 처음 등재할 경우
            if (!current.hasChild(c)) {
                current.child[c - 'A'] = new TrieNode();
            }
            // 단어를 잇는 과정
            current = current.getChild(c);
        }

        // 단어의 끝 표시함
        current.isEnd = true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());

        // 단어 저장
        words = new String[W];
        for (int i = 0; i < W; i++) {
            words[i] = br.readLine();
            insert(words[i]);
        }

        // 점수 설정
        scores = new int[9];
        scores[0] = 0; scores[1] = 0; scores[2] = 0;
        scores[3] = 1; scores[4] = 1;
        scores[5] = 2; scores[6] = 3; scores[7] = 5; scores[8] = 11;

        br.readLine();

        B = Integer.parseInt(br.readLine());
        for (int num = 0; num < B; num++) {
            sum = 0;
            answer = "";
            count = 0;

            board = new char[4][4];
            for (int i = 0; i < 4; i++) {
                String temp = br.readLine();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = temp.charAt(j);
                }
            }

            visited = new boolean[4][4];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (root.hasChild(board[i][j])) {
                        search(i, j, root.getChild(board[i][j]));
                    }
                }
            }

            System.out.println(sum + " " + answer + " " + count);

            root.clearHit();

            if (count != B - 1)
                br.readLine();
        }
    }
}

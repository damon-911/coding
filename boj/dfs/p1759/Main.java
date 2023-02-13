package boj.dfs.p1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C, length, vowel, consonant;
    static boolean[] visited;
    static char[] input;

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        if (isVowel(input[index]))
            vowel++;
        else
            consonant++;
        length++;

        // 2. 목적지인가
        if (length == L) {
            if (vowel >= 1 && consonant >= 2) {
                for (int i = 0; i < C; i++) {
                    if (visited[i])
                        System.out.print(input[i]);
                }
                System.out.println();
            }
        }
        else {
            // 3. 연결된 곳을 순회
            for (int i = index + 1; i < C; i++) {
                // 4. 갈 수 있는가?
                // 5. 간다
                dfs(i);
            }
        }

        // 6. 체크아웃
        visited[index] = false;
        if (isVowel(input[index]))
            vowel--;
        else
            consonant--;
        length--;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        input = new char[C];
        for (int i = 0; i < C; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        // 알파벳 순서대로 정렬
        Arrays.sort(input);

        for (int i = 0; i <= C - L; i++) {
            length = 0;
            vowel = 0;
            consonant = 0;
            dfs(i);
        }
    }
}

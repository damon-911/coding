package boj.dfs.p1062;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K, max, selectedCount;
    static boolean[] visited;
    static String[] words;

    // 총 몇 개의 단어를 읽을 수 있는지 확인
    static int countReadable() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (!visited[word.charAt(j) - 'a']) {
                    flag = false;
                    break;
                }
            }

            // 단어를 이루는 모든 글자를 배웠을 경우
            if (flag)
                count++;
        }

        return count;
    }

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        selectedCount++;

        // 2. 목적지인가
        if (selectedCount == K) {
            max = Math.max(max, countReadable());
        }
        else {
            // 3. 연결된 곳을 순회
            for (int i = index + 1; i < 26; i++) {
                // 4. 갈 수 있는가?
                if (!visited[i]) {
                    // 5. 간다
                    dfs(i);
                }
            }
        }

        // 6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1062/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        max = 0;
        selectedCount = 5;

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;

        words = new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken().replaceAll("[acint]", "");
        }

        // 'a', 'c', 'i', 'n', 't' 는 무조건 가르쳐야 한다.
        if (K < 5)
            System.out.println(0);
        else if (K == 5)
            System.out.println(countReadable());
        else if (K == 26)
            System.out.println(N);
        else {
            for (int start = 0; start < 26; start++) {
                if (!visited[start])
                    dfs(start);
            }
            System.out.println(max);
        }
    }
}

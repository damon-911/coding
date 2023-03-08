package boj.combinatorics.p1256;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] pascal = new int[201][201];
    static StringBuilder answer = new StringBuilder();

    static int combination(int total, int choose) {
        if (choose == 0 || total == choose)
            return 1;
        else if (pascal[total][choose] != 0) {
            return pascal[total][choose];
        }
        else {
            return pascal[total][choose] = (int) Math.min(1e9, combination(total - 1, choose) + combination(total - 1, choose - 1));
        }
    }

    static void query(int n, int m, int k) {
        if (n + m == 0) {
            return;
        }
        else if (n == 0) {
            answer.append("z");
            query(n, m - 1, k);
        }
        else if (m == 0) {
            answer.append("a");
            query(n - 1, m, k);
        }
        else {
            // limit : 해당 자리를 제외한 나머지 자리에 올 수 있는 경우의 수
            int limit = combination(n + m - 1, m);
            if (limit >= k) {
                // 이제 문자열에는 (n - 1)개의 'a'가 필요함
                answer.append("a");
                query(n - 1, m, k);
            }
            else {
                // 이제 문자열에는 (m - 1)개의 'z'가 필요하고 limit 뒤에서부터 순서를 찾아야 함
                answer.append("z");
                query(n, m - 1, k - limit);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p1256/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (combination(N + M, M) < K)
            System.out.println(-1);
        else {
           query(N, M ,K);
           System.out.println(answer);
        }
    }
}
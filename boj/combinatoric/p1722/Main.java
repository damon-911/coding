package boj.combinatoric.p1722;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, flag;
    static int[] num;
    static boolean[] visited;

    static long perm(int num) {
        if (num == 0)
            return 1;
        return num * perm(--num);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatoric/p1722/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());

        flag = Integer.parseInt(st.nextToken());
        if (flag == 1) {
            long target = Long.parseLong(st.nextToken());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!visited[j]) {
                        // target이 경계보다 클 경우
                        if (target > perm(N - i - 1)) {
                            target -= perm(N - i - 1);
                        }
                        // target이 경계보다 작을 경우
                        else {
                            sb.append(j);
                            sb.append(" ");
                            visited[j] = true;
                            break;
                        }
                    }
                }
            }

            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
        else {
            num = new int[N];
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            long result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < num[i]; j++) {
                    if (!visited[j])
                        result += perm(N - i - 1);
                }

                visited[num[i]] = true;
            }

            System.out.println(result + 1);
        }
    }
}
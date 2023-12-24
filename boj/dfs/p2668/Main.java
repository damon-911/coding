package boj.dfs.p2668;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static int[] nums;
    static boolean[] visited;
    static List<Integer> answer;

    static void dfs(int cur, int dest) {
        // 사이클이 발생했다면
        if (nums[cur] == dest) {
            answer.add(dest);
        }

        if (!visited[nums[cur]]) {
            visited[nums[cur]] = true;
            dfs(nums[cur], dest);
            visited[nums[cur]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p2668/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        answer.forEach(System.out::println);
    }
}

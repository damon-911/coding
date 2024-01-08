package boj.dfs.p1174;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int[] nums = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    static List<Long> result = new ArrayList<>();

    static void dfs(long sum, int depth) {
        if (!result.contains(sum)) {
            result.add(sum);
        }

        if (depth >= 10) {
            return;
        }

        dfs((sum * 10) + nums[depth], depth + 1);
        dfs(sum, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dfs/p1174/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int order = Integer.parseInt(br.readLine());

        if (order >= 1024) {
            System.out.println(-1);
            return;
        }

        dfs(0, 0);

        Collections.sort(result);

        System.out.println(result.get(order - 1));
    }
}

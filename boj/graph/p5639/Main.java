package boj.graph.p5639;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> tree;

    static void postOrder(int cur, int end) {
        if (cur > end) {
            return;
        }

        int mid = cur + 1;
        while (mid <= end && tree.get(mid) < tree.get(cur)) {
            mid++;
        }

        postOrder(cur + 1, mid - 1);
        postOrder(mid, end);

        System.out.println(tree.get(cur));
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p5639/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new ArrayList<>();
        while (br.ready()) {
            int num = Integer.parseInt(br.readLine());
            tree.add(num);
        }

        postOrder(0, tree.size() - 1);
    }
}

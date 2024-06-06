package boj.data_structure.p9934;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int K, max;
    static int[] sequence;
    static List<List<Integer>> nodeList;

    static void search(int start, int end, int depth) {
        if (depth == K) {
            return;
        }

        int mid = (start + end) / 2;

        nodeList.get(depth).add(sequence[mid]);

        search(start, mid - 1, depth + 1);
        search(mid + 1, end, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p9934/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        max = (int) Math.pow(2, K) - 1;

        sequence = new int[max];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < max; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        nodeList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            nodeList.add(new ArrayList<>());
        }

        search(0, max - 1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int j : nodeList.get(i)) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

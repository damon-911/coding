package boj.sorting.p1744;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N;
    static int sum = 0;
    static List<Integer> positive = new ArrayList<>();
    static List<Integer> negative = new ArrayList<>();

    private static void makeMax() {
        // 양수끼리만 곱하기 (단, 1이 있다면 묶지 않고 더해줌)
        int idx = 0;
        while (idx < positive.size()) {
            if (idx + 1 < positive.size() && positive.get(idx) != 1 && positive.get(idx + 1) != 1)
                sum += positive.get(idx++) * positive.get(idx++);
            else
                sum += positive.get(idx++);
        }

        // 음수끼리만 곱하기
        idx = 0;
        while (idx < negative.size()) {
            if (idx + 1 < negative.size())
                sum += negative.get(idx++) * negative.get(idx++);
            else
                sum += negative.get(idx++);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p1744/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0)
                positive.add(num);
            else
                negative.add(num);
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        makeMax();

        System.out.println(sum);
    }
}
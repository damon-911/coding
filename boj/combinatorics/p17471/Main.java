package boj.combinatorics.p17471;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] peopleNum;
    static boolean selected[];
    static boolean visited[];
    static List<List<Integer>> graph;
    static int result = Integer.MAX_VALUE;

    // 선거구의 인구 차 구하기
    static void getDiff() {
        int numA = 0, numB = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                numA += peopleNum[i];
            } else {
                numB += peopleNum[i];
            }
        }
        int diff = Math.abs(numA - numB);
        result = Math.min(result, diff);
    }

    // 해당 구역이 연결되었는지 확인
    static boolean check(List<Integer> list) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(list.get(0));

        visited = new boolean[N + 1];
        visited[list.get(0)] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (list.contains(next) && !visited[next]) { // 선거구에 해당하고, 아직 미방문
                    queue.offer(next);
                    visited[next] = true;
                    count++;
                }
            }
        }

        if (count == list.size()) {
            return true;
        }
        return false;
    }

    // 선거구 나누기
    static void divide(int idx) {
        if (idx > N) {
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (selected[i]) {
                    aList.add(i);
                } else {
                    bList.add(i);
                }
            }

            if (aList.size() == 0 || bList.size() == 0) {
                return;
            }

            if (check(aList) && check(bList)) {
                getDiff();
            }

            return;
        }

        selected[idx] = true;
        divide(idx + 1);

        selected[idx] = false;
        divide(idx + 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/combinatorics/p17471/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        peopleNum = new int[N + 1];
        selected = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            peopleNum[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        divide(1);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
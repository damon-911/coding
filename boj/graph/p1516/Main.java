package boj.graph.p1516;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Build[] buildings;
    static int[] inDegree, minTime;
    static Queue<Build> queue;

    static class Build {
        int time;
        ArrayList<Integer> pred;

        public Build(int time, ArrayList<Integer> pred) {
            this.time = time;
            this.pred = pred;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ArrayList<Integer> getPred() {
            return pred;
        }
    }

    static void getMinTime() {
        while (!queue.isEmpty()) {
            Build build = queue.poll();

            for (int i = 0; i < build.pred.size(); i++) {
                int dest = build.pred.get(i);

                // dest 건물을 짓는 데 걸리는 최소 시간 < dest 건물 짓는데 필요한 시간 + 현재까지 건물 짓는데 걸린 시간
                if (minTime[dest] < buildings[dest].time + build.time) {
                    minTime[dest] = buildings[dest].time + build.time;
                }

                // inDegree 를 1씩 감소시킨 각 정점이 inDegree = 0 이라면 큐에 넣는다
                if (--inDegree[dest] == 0) {
                    queue.offer(buildings[dest]);
                    // dest 건물 짓기까지 걸린 시간 최신화
                    buildings[dest].time = minTime[dest];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(minTime[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/graph/p1516/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        buildings = new Build[N + 1];
        for (int i = 0; i <= N; i++) {
            buildings[i] = new Build(0, new ArrayList<>());
        }

        inDegree = new int[N + 1];
        minTime = new int[N + 1];
        queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            buildings[i].setTime(t);
            minTime[i] = t;

            int temp;
            while ((temp = Integer.parseInt(st.nextToken())) != -1) {
                // temp를 선행자로 가지는 건물 번호를 저장한다.
                buildings[temp].getPred().add(i);
                inDegree[i]++;
            }

            if (inDegree[i] == 0)
                queue.offer(buildings[i]);
        }

        getMinTime();
    }
}

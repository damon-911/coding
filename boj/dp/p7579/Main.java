package boj.dp.p7579;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static App[] apps;

    static class App {
        int memory;
        int cost;

        public App(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p7579/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        apps = new App[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i] = new App(Integer.parseInt(st.nextToken()), 0);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i].setCost(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(apps, new Comparator<App>() {
            @Override
            public int compare(App o1, App o2) {
                return o1.cost - o2.cost;
            }
        });

        System.out.println(apps);
    }
}

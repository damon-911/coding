package boj.data_structure.p1202;

import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static Dia[] dias;
    static int[] bags;

    static class Dia implements Comparable<Dia> {
        int weight;
        int price;

        public Dia(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public int compareTo(Dia d) {
            return weight - d.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dias = new Dia[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dias[i] = new Dia(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

        // 보석 무게 오름차순 정렬
        Arrays.sort(dias);

        // 가방 용량 오름차순 정렬
        Arrays.sort(bags);

        // 가치가 높은 보석 기준 우선순위 큐
        PriorityQueue<Dia> pq = new PriorityQueue<>(Comparator.comparingInt(Dia::getPrice).reversed());

        int diaIndex = 0;
        long result = 0;

        // 1. 남은 가방 중 제일 작은 가방을 선택 (정렬)
        for (int i = 0; i < K; i++) {
            int currentBag = bags[i];

            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 (힙을 사용)
            while (diaIndex < N && dias[diaIndex].weight <= currentBag) {
                pq.add(dias[diaIndex++]);
            }

            if (!pq.isEmpty()) {
                result += pq.poll().price;
            }
        }

        System.out.println(result);
    }
}

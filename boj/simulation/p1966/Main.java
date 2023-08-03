package boj.simulation.p1966;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static LinkedList<Document> queue;
    static StringBuilder sb = new StringBuilder();

    static class Document {
        int id;
        int priority;

        public Document(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/simulation/p1966/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < num; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                queue.offer(new Document(i, Integer.parseInt(st.nextToken())));
            }

            int count = 0;

            while (!queue.isEmpty()) {
                Document document = queue.poll();
                boolean isMax = true;

                // 큐에 남아있는 원소들과 중요도를 비교
                for (int i = 0; i < queue.size(); i++) {
                    // 처음 뽑은 원소보다 큐에 있는 i번째 원소가 중요도가 클 경우
                    if (document.priority < queue.get(i).priority) {
                        // 뽑은 원소 및 i 이전의 원소들을 뒤로 보낸다.
                        queue.offer(document);
                        for (int j = 0; j < i; j++) {
                            queue.offer(queue.poll());
                        }
                        isMax = false;
                        break;
                    }
                }

                if (isMax == false) {
                    continue;
                }

                count++;
                if (document.id == M) {
                    break;
                }
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb.toString());
    }
}

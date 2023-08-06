package boj.data_structure.p1927;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static List<Integer> heap;

    static int delete() {
        // 힙이 비어있으면 0 리턴
        if (heap.size() <= 1) {
            return 0;
        }

        // 삭제할 노드 (루트 노드)
        int deleteitem = heap.get(1);

        // 마지막 노드를 루트에 삽입하고 마지막 노드 삭제
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int pos = 1;

        while ((pos * 2) < heap.size()) {
            int min = heap.get(pos * 2);
            int minPos = pos * 2;

            // 오른쪽 자식이 왼쪽 자식보다 더 작을때 오른쪽 자식을 부모와 바꿔줄 자식으로 지정
            if (((pos * 2 + 1) < heap.size()) && min > heap.get(pos * 2 + 1)) {
                min = heap.get(pos * 2 + 1);
                minPos = pos * 2 + 1;
            }

            // 부모가 더 작다면 break
            if (min > heap.get(pos))
                break;

            // 부모 자식 교환
            int tmp = heap.get(pos);
            heap.set(pos, heap.get(minPos));
            heap.set(minPos, tmp);
            pos = minPos;
        }

        return deleteitem;
    }

    static void insert(int num) {
        heap.add(num);

        int idx = heap.size() - 1;

        // 새로 삽입한 노드의 위치가 루트가 아니고 부모 노드가 자식 노드보다 크다면
        while (idx > 1 && heap.get(idx / 2) > heap.get(idx)) {
            int tmp = heap.get(idx / 2);
            heap.set(idx / 2, num);
            heap.set(idx, tmp);
            idx /= 2;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p1927/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        heap = new ArrayList<>();
        heap.add(0); // 0번째 인덱스는 사용 안함

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0)
                System.out.println(delete());
            else
                insert(num);
        }
    }
}

package boj.data_structure.p11279;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static MaxHeap heap;

    static class MaxHeap {

        List<Integer> list;

        public MaxHeap() {
            list = new ArrayList<>(100001);
            list.add(0);
        }

        public void insert(int val) {
            // 1. 마지막에 추가
            list.add(val);

            // 2. 부모와 조건 비교, 교환
            int current = list.size() - 1;
            int parent = current / 2;

            while (true) {
                // current 가 root 거나 부모자식 조건을 만족하면 break
                if (current == 1 || list.get(parent) >= list.get(current)) {
                    break;
                }

                int temp = list.get(parent);
                list.set(parent, list.get(current));
                list.set(current, temp);

                current = parent;
                parent = current / 2;
            }
        }

        public int delete() {
            // 1. root 제거
            int top = list.get(1);

            // 2. 마지막 노드를 root 로 이동
            list.set(1, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            // 3. 왼쪽, 오른쪽 중 조건이 안 맞는 것을 선택한 후 조건에 맞게 swap
            int currentNode = 1;
            while (true) {
                int leftNode = currentNode * 2;
                int rightNode = currentNode * 2 + 1;

                // 현재 데이터 size를 넘어가면 break
                if (leftNode >= list.size()) {
                    break;
                }

                // 왼쪽 자식과 오른쪽 자식 중 더 큰 것을 찾는 과정
                int targetNode = leftNode;
                int targetValue = list.get(leftNode);

                if (rightNode < list.size() && targetValue < list.get(rightNode)) {
                    targetNode = rightNode;
                    targetValue = list.get(rightNode);
                }

                // 비교 후 조건에 맞게 swap
                if (list.get(currentNode) < targetValue) {
                    int temp = list.get(currentNode);
                    list.set(currentNode, list.get(targetNode));
                    list.set(targetNode, temp);
                    currentNode = targetNode;
                }
                else {
                    break;
                }
            }

            return top;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p11279/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        heap = new MaxHeap();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());

            if (input > 0) {
                heap.insert(input);
            }
            else if (input == 0) {
                if (heap.list.size() == 1)
                    System.out.println(0);
                else {
                    System.out.println(heap.delete());
                }
            }
        }
    }
}

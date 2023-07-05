package programmers.data_structure.길찾기게임;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    static int[][] answer;
    static int idx;

    static class Node implements Comparable<Node> {
        int num;
        int x;
        int y;
        Node left; // 왼쪽 자식노드
        Node right; // 오른쪽 자식노드

        public Node(int num, int x, int y, Node left, Node right) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }

        // y좌표가 큰 순서대로, 같다면 x좌표가 작은 순서대로
        @Override
        public int compareTo(Node node) {
            return node.y != this.y ? node.y - this.y : this.x - node.x;
        }
    }

    // 전위 순회
    static void preOrder(Node root) {
        if (root != null) {
            answer[0][idx++] = root.num;
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // 후위 순회
    static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            answer[1][idx++] = root.num;
        }
    }

    static void insertNode(Node parent, Node child) {
        if (parent.x > child.x) { // 왼쪽 자식노드
            if (parent.left == null)
                parent.left = child;
            else
                insertNode(parent.left, child);
        } else { // 오른쪽 자식노드
            if (parent.right == null)
                parent.right = child;
            else
                insertNode(parent.right, child);
        }
    }

    public static int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            list.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null));
        }
        Collections.sort(list);

        Node root = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            insertNode(root, list.get(i));
        }

        idx = 0;
        preOrder(root);

        idx = 0;
        postOrder(root);

        return answer;
    }

    public static void main(String[] args) {
        int[][] nodeinfo = {
                { 5, 3 },
                { 11, 5 },
                { 13, 3 },
                { 3, 5 },
                { 6, 1 },
                { 1, 3 },
                { 8, 6 },
                { 7, 2 },
                { 2, 2 }
        };

        int[][] result = solution(nodeinfo);
        for (int i = 0; i < 2; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
package boj.data_structure.p1991;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Node node;

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static void preOrder(Node n) {
        if (n == null)
            return;
        System.out.print(n.value);
        preOrder(n.left);
        preOrder(n.right);
    }

    static void inOrder(Node n) {
        if (n == null)
            return;
        inOrder(n.left);
        System.out.print(n.value);
        inOrder(n.right);
    }

    static void postOrder(Node n) {
        if (n == null)
            return;
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.value);
    }

    static void insertNode(Node parent, String root, String left, String right) {
        if (parent.value.equals(root)) {
            parent.left = (left.equals(".") ? null : new Node(left, null, null));
            parent.right = (right.equals(".") ? null : new Node(right, null, null));
        } else {
            if (parent.left != null)
                insertNode(parent.left, root, left, right);
            if (parent.right != null)
                insertNode(parent.right, root, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        node = new Node("A", null, null);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            insertNode(node, root, left, right);
        }

        preOrder(node);
        System.out.println();

        inOrder(node);
        System.out.println();

        postOrder(node);
        System.out.println();
    }
}

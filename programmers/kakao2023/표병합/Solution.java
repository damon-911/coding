package programmers.kakao2023.표병합;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static String[] board = new String[2501];
    static int[] parent = new int[2501];

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB)
            parent[b] = a;
    }

    private static int convert(int x, int y) {
        return 50 * (x - 1) + y;
    }

    public static String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        for (int i = 1; i <= 2500; i++) {
            board[i] = "";
            parent[i] = i;
        }

        for (String command : commands) {
            String[] check = command.split(" ");

            if (check[0].equals("UPDATE")) {
                if (check.length > 3) {
                    int num = convert(Integer.parseInt(check[1]), Integer.parseInt(check[2]));
                    board[find(num)] = check[3];
                } else {
                    for (int i = 1; i <= 2500; i++) {
                        if (board[i].equals(check[1]))
                            board[i] = check[2];
                    }
                }
            } else if (check[0].equals("MERGE")) {
                int num1 = convert(Integer.parseInt(check[1]), Integer.parseInt(check[2]));
                int num2 = convert(Integer.parseInt(check[3]), Integer.parseInt(check[4]));

                int root1 = find(num1);
                int root2 = find(num2);

                // 이미 같이 병합되어 있다면
                if (root1 == root2)
                    continue;

                union(root1, root2);

                String value = board[root1].isBlank() ? board[root2] : board[root1];
                board[root1] = value;
                board[root2] = "";
            } else if (check[0].equals("UNMERGE")) {
                int num = convert(Integer.parseInt(check[1]), Integer.parseInt(check[2]));
                int root = find(num);
                String value = board[root];

                board[root] = "";
                board[num] = value;

                // 같이 병합되어 있던 모든 셀들 찾기
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (find(i) == root)
                        deleteList.add(i);
                }

                for (Integer i : deleteList)
                    parent[i] = i;
            } else {
                int num = convert(Integer.parseInt(check[1]), Integer.parseInt(check[2]));
                int root = find(num);
                if (board[root].isBlank())
                    answer.add("EMPTY");
                else
                    answer.add(board[root]);
            }
        }

        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] commands = {
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",
                "MERGE 1 2 1 3",
                "MERGE 1 3 1 4",
                "UPDATE korean hansik",
                "UPDATE 1 3 group",
                "UNMERGE 1 4",
                "PRINT 1 3",
                "PRINT 1 4"
        };

        System.out.println(Arrays.toString(solution(commands)));
    }
}
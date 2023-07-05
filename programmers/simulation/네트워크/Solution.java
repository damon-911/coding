package programmers.simulation.네트워크;

public class Solution {

    static int[] parent;

    private static void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);

        if (root1 > root2)
            parent[root1] = root2;
        else
            parent[root2] = root1;
    }

    private static int find(int num) {
        if (parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (computers[i][j] == 1)
                    union(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] == i)
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {
                { 1, 1, 0 },
                { 1, 1, 0 },
                { 0, 0, 1 }
        };

        System.out.println(solution(n, computers));
    }
}
package programmers.data_structure.크레인인형뽑기게임;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    static Stack<Integer> stack;
    static List<LinkedList<Integer>> boardList;

    static int game(int[] moves) {
        int result = 0;

        for (int move : moves) {
            if (boardList.get(move).size() > 0) {
                int num = boardList.get(move).removeFirst();

                if (!stack.empty() && stack.peek() == num) {
                    stack.pop();
                    result += 2;
                } else
                    stack.push(num);
            }
        }

        return result;
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        stack = new Stack<>();
        boardList = new ArrayList<>();

        for (int i = 0; i <= board.length; i++) {
            boardList.add(new LinkedList<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0)
                    boardList.get(i + 1).add(board[j][i]);
            }
        }

        answer = game(moves);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 3 },
                { 0, 2, 5, 0, 1 },
                { 4, 2, 4, 4, 2 },
                { 3, 5, 1, 3, 1 }
        };
        int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };

        System.out.println(solution(board, moves));
    }
}
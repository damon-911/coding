package programmers.simulation.퍼즐조각채우기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1 };

    static ArrayList<ArrayList<Point>> empty = new ArrayList<>();
    static ArrayList<ArrayList<Point>> puzzle = new ArrayList<>();
    static boolean[][] visited;
    static int boardSize;

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x == p.x)
                return this.y - p.y;

            return this.x - p.x;
        }
    }

    static ArrayList<Point> findSpace(int[][] board, int x, int y, int type) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> space = new ArrayList<>();

        q.add(new Point(x, y));
        visited[x][y] = true;

        // 첫 공간 좌표 기준을 (0, 0)으로 함
        space.add(new Point(0, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int tx = p.x + MX[i];
                int ty = p.y + MY[i];

                if (tx >= 0 && tx < boardSize && ty >= 0 && ty < boardSize) {
                    if (!visited[tx][ty] && board[tx][ty] == type) {
                        q.add(new Point(tx, ty));

                        visited[tx][ty] = true;

                        space.add(new Point(tx - x, ty - y));
                    }
                }
            }
        }

        Collections.sort(space);

        return space;
    }

    static boolean isFit(ArrayList<Point> puzzleCheck, ArrayList<Point> emptyCheck) {
        // 90도씩 총 4번씩 수행
        for (int i = 0; i < 4; i++) {
            int startX = puzzleCheck.get(0).x;
            int startY = puzzleCheck.get(0).y;

            // 회전하면서 변한 좌표를 다시 (0, 0) 기준으로 수정
            for (int j = 0; j < puzzleCheck.size(); j++) {
                puzzleCheck.get(j).x -= startX;
                puzzleCheck.get(j).y -= startY;
            }

            boolean found = true;

            for (int j = 0; j < emptyCheck.size(); j++) {
                Point puzzlePoint = puzzleCheck.get(j);
                Point emptyPoint = emptyCheck.get(j);

                if (puzzlePoint.x != emptyPoint.x || puzzlePoint.y != emptyPoint.y) {
                    found = false;
                    break;
                }
            }

            if (found)
                return true;
            else {
                // 90도 회전
                for (int j = 0; j < puzzleCheck.size(); j++) {
                    int temp = puzzleCheck.get(j).x;
                    puzzleCheck.get(j).x = puzzleCheck.get(j).y;
                    puzzleCheck.get(j).y = temp * -1;
                }

                Collections.sort(puzzleCheck);
            }
        }

        return false;
    }

    public static int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        boardSize = game_board.length;

        visited = new boolean[boardSize][boardSize];

        // empty 체크
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (game_board[i][j] == 0 && !visited[i][j])
                    empty.add(findSpace(game_board, i, j, 0));
            }
        }

        // 다시 false로 모두 채움
        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(visited[i], false);
        }

        // puzzle 체크
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (table[i][j] == 1 && !visited[i][j])
                    puzzle.add(findSpace(table, i, j, 1));
            }
        }

        boolean[] used = new boolean[empty.size()];

        // empty와 puzzle 비교
        for (int i = 0; i < puzzle.size(); i++) {
            ArrayList<Point> puzzleCheck = puzzle.get(i);

            for (int j = 0; j < empty.size(); j++) {
                ArrayList<Point> emptyCheck = empty.get(j);

                if (puzzleCheck.size() == emptyCheck.size() && !used[j]) {
                    if (isFit(puzzleCheck, emptyCheck)) {
                        answer += puzzleCheck.size();
                        used[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] game_board = {
                { 1, 1, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 1 },
                { 1, 1, 0, 1, 1, 1 },
                { 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 1, 0, 0 }
        };
        int[][] table = {
                { 1, 0, 0, 1, 1, 0 },
                { 1, 0, 1, 0, 1, 0 },
                { 0, 1, 1, 0, 1, 1 },
                { 0, 0, 1, 0, 0, 0 },
                { 1, 1, 0, 1, 1, 0 },
                { 0, 1, 0, 0, 0, 0 }
        };

        System.out.println(solution(game_board, table));
    }
}
package programmers.pccp.수레움직이기;

public class Solution {

    static final int[] MX = { 1, -1, 0, 0, 0 };
    static final int[] MY = { 0, 0, 1, -1, 0 };

    static final int RED = 0;
    static final int BLUE = 1;

    static final int RED_GOAL = 3;
    static final int BLUE_GOAL = 4;
    static final int WALL = 5;

    static int row, col, answer;
    static boolean[][][] visited;

    static boolean isSwiching(int[] redPos, int[] nextRedPos, int[] bluePos, int[] nextBluePos) {
        if ((redPos[0] == nextBluePos[0] && redPos[1] == nextBluePos[1])
                && (bluePos[0] == nextRedPos[0] && bluePos[1] == nextRedPos[1])) {
            return true;
        }
        return false;
    }

    static boolean isSamePlace(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    static void findMinTurn(int redX, int redY, int blueX, int blueY, int turn, int[][] maze) {
        if (maze[redX][redY] == RED_GOAL && maze[blueX][blueY] == BLUE_GOAL) {
            answer = Math.min(answer, turn);
            return;
        }

        for (int i = 0; i < 5; i++) {
            int nextRedX = redX + MX[i];
            int nextRedY = redY + MY[i];

            if (isOutOfBounds(nextRedX, nextRedY)
                    || (maze[nextRedX][nextRedY] != RED_GOAL && visited[nextRedX][nextRedY][RED])
                    || maze[nextRedX][nextRedY] == WALL) {
                continue;
            }

            visited[nextRedX][nextRedY][RED] = true;

            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 4) {
                    break;
                }

                int nextBlueX = blueX + MX[j];
                int nextBlueY = blueY + MY[j];

                if (isOutOfBounds(nextBlueX, nextBlueY)
                        || (maze[nextBlueX][nextBlueY] != BLUE_GOAL && visited[nextBlueX][nextBlueY][BLUE])
                        || maze[nextBlueX][nextBlueY] == WALL
                        || isSamePlace(nextRedX, nextRedY, nextBlueX, nextBlueY)
                        || isSwiching(new int[] { redX, redY }, new int[] { nextRedX, nextRedY },
                                new int[] { blueX, blueY }, new int[] { nextBlueX, nextBlueY })) {
                    continue;
                }

                visited[nextBlueX][nextBlueY][BLUE] = true;
                findMinTurn(nextRedX, nextRedY, nextBlueX, nextBlueY, turn + 1, maze);
                visited[nextBlueX][nextBlueY][BLUE] = false;
            }

            visited[nextRedX][nextRedY][RED] = false;
        }
    }

    public static int solution(int[][] maze) {
        answer = Integer.MAX_VALUE;

        row = maze.length;
        col = maze[0].length;

        int[] startR = new int[2];
        int[] startB = new int[2];
        visited = new boolean[row][col][2];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 1) {
                    startR[0] = i;
                    startR[1] = j;
                    visited[i][j][RED] = true;
                } else if (maze[i][j] == 2) {
                    startB[0] = i;
                    startB[1] = j;
                    visited[i][j][BLUE] = true;
                }
            }
        }

        findMinTurn(startR[0], startR[1], startB[0], startB[1], 0, maze);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) {
        int[][] maze = {
                { 1, 4 },
                { 0, 0 },
                { 2, 3 }
        };

        System.out.println(solution(maze));
    }
}
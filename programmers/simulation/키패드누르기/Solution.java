package programmers.simulation.키패드누르기;

public class Solution {

    public static int dis(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) + Math.abs(startY - endY);
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";

        int leftX = 3;
        int leftY = 0;

        int rightX = 3;
        int rightY = 2;

        for (int num : numbers) {
            if (num == 0) {
                num = 11;
            }

            int numX = (num - 1) / 3;
            int numY = (num - 1) % 3;

            if (num % 3 == 1) {
                answer += "L";
                leftX = numX;
                leftY = numY;
            } else if (num % 3 == 0) {
                answer += "R";
                rightX = numX;
                rightY = numY;
            } else {
                int disLeft = Math.abs(leftX - numX) + Math.abs(leftY - numY);
                int disRight = Math.abs(rightX - numX) + Math.abs(rightY - numY);

                if (disLeft < disRight) {
                    answer += "L";
                    leftX = numX;
                    leftY = numY;
                } else if (disLeft > disRight) {
                    answer += "R";
                    rightX = numX;
                    rightY = numY;
                } else {
                    if (hand.equals("left")) {
                        answer += "L";
                        leftX = numX;
                        leftY = numY;
                    } else {
                        answer += "R";
                        rightX = numX;
                        rightY = numY;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
        String hand = "right";

        System.out.println(solution(numbers, hand));
    }
}
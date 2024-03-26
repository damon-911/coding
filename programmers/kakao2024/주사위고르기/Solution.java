package programmers.kakao2024.주사위고르기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    static int N;
    static int[] answer;
    static int[][] dices;
    static List<Integer> me;
    static List<Integer> you;

    static int max = Integer.MIN_VALUE;
    static List<Integer> choice = new ArrayList<>();

    public static void makeSum(int[][] dice, List<Integer> list, int depth, int sum) {
        if (depth == N / 2) {
            list.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            int newSum = sum + dice[depth][i];
            makeSum(dice, list, depth + 1, newSum);
        }
    }

    static void init() {
        me = new ArrayList<>();
        you = new ArrayList<>();

        int[][] myDice = new int[N / 2][6];
        int[][] yourDice = new int[N / 2][6];

        int myIdx = 0, yourIdx = 0;

        for (int i = 0; i < N; i++) {
            if (choice.contains(i)) {
                myDice[myIdx++] = dices[i];
            } else {
                yourDice[yourIdx++] = dices[i];
            }
        }

        makeSum(myDice, me, 0, 0);
        makeSum(yourDice, you, 0, 0);
    }

    static int calculateWinningRate() {
        int count = 0;

        init();

        Collections.sort(you);

        // 이분 탐색으로 you에 number보다 작은 숫자가 몇 개 있는지 체크
        for (int i = 0; i < me.size(); i++) {
            int number = me.get(i);

            int left = 0;
            int right = you.size() - 1;

            int index = Integer.MIN_VALUE;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (you.get(mid) < number) {
                    left = mid + 1;
                    index = Math.max(index, mid);
                } else {
                    right = mid - 1;
                }
            }

            if (index != Integer.MIN_VALUE) {
                count += index + 1;
            }
        }

        return count;
    }

    static void chooseDice(int depth, int cur) {
        if (depth == N / 2) {
            int winning = calculateWinningRate();
            if (max < winning) {
                max = winning;
                for (int i = 0; i < choice.size(); i++) {
                    answer[i] = choice.get(i) + 1;
                }
            }
            return;
        }

        for (int i = cur; i < N; i++) {
            choice.add(i);
            chooseDice(depth + 1, i + 1);
            choice.remove(choice.size() - 1);
        }
    }

    public static int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        answer = new int[N / 2];

        chooseDice(0, 0);

        return answer;
    }

    public static void main(String[] args) {
        int[][] dice = {
                { 1, 2, 3, 4, 5, 6 },
                { 3, 3, 3, 3, 4, 4 },
                { 1, 3, 3, 4, 4, 4 },
                { 1, 1, 4, 4, 5, 5 }
        };

        System.out.println(Arrays.toString(solution(dice)));
    }
}
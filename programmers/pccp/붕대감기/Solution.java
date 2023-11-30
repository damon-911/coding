package programmers.pccp.붕대감기;

public class Solution {

    public static int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;

        int time = 1;
        int cont = 0;
        int atkIdx = 0;

        while (time <= attacks[attacks.length - 1][0]) {
            if (answer <= 0) {
                break;
            }

            if (time == attacks[atkIdx][0]) {
                answer -= attacks[atkIdx][1];
                cont = 0;
                time++;
                atkIdx++;
                continue;
            }

            if (answer >= health) {
                answer = health;
                time++;
                continue;
            }

            answer += bandage[1];
            cont++;
            time++;

            if (cont == bandage[0]) {
                answer += bandage[2];
                cont = 0;
            }
        }

        if (answer <= 0) {
            return -1;
        } else {
            return answer;
        }
    }

    public static void main(String[] args) {
        int[] bandage = { 5, 1, 5 };
        int health = 30;
        int[][] attacks = {
                { 2, 10 },
                { 9, 15 },
                { 10, 5 },
                { 11, 5 }
        };

        System.out.println(solution(bandage, health, attacks));
    }
}
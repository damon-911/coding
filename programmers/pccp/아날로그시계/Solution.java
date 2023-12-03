package programmers.pccp.아날로그시계;

public class Solution {

    // 0시 0분 0초 ~ h시 m분 s초까지 알람 횟수
    static int getAlarm(int h, int m, int s) {
        // 12시 이상이면 나눠서 계산
        if (h >= 12) {
            return getAlarm(11, 59, 59) + getAlarm(h - 12, m, s);
        }

        // 0분~58분 까지 2번 겹침
        // 59분~0분까지는 초침이 분침을 정각 전에 따라잡지 못하므로 1번 겹침
        // 0시 0분 0초에는 시침/분침과 동시에 겹치므로 1회 차감
        // 따라서, 1시간 당 알람 횟수 = 시간 * 2 + 시간 + 분 * 2 - 1
        int count = (h * 59 * 2) + h + (m * 2) - 1;

        int posMinute = (h * 3600 + m * 60 + s) * 12;
        int posHour = h * 3600 + m * 60 + s;

        // 초침이 분침을 지나쳤다면
        if (s * 720 >= posMinute % 43200) {
            count++;
        }

        // 초침이 시침을 지나쳤다면
        if (s * 720 >= posHour % 43200) {
            count++;
        }

        return count;
    }

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = getAlarm(h2, m2, s2) - getAlarm(h1, m1, s1);

        // 시작 시간이 정각인 경우 정각 알림까지 빼지므로 1을 더해준다
        if (m1 == 0 && s1 == 0) {
            answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int h1 = 0;
        int m1 = 5;
        int s1 = 30;
        int h2 = 0;
        int m2 = 7;
        int s2 = 0;

        System.out.println(solution(h1, m1, s1, h2, m2, s2));
    }
}
package programmers.string.광고삽입;

import java.util.Arrays;

public class Solution {

    static String convertStrTime(int time) {
        int H = time / 3600;
        int M = (time - 3600 * H) / 60;
        int S = time - 3600 * H - 60 * M;

        return (H < 10 ? "0" : "") + H + ":" +
                (M < 10 ? "0" : "") + M + ":" +
                (S < 10 ? "0" : "") + S;
    }

    static int convertIntTime(String strTime) {
        int[] time = Arrays
                .stream(strTime.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();

        return 3600 * time[0] + 60 * time[1] + time[2];
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convertIntTime(play_time);
        int advTime = convertIntTime(adv_time);
        int[] times = new int[360_000];

        for (String log : logs) {
            String[] splitLog = log.split("-");
            int startTime = convertIntTime(splitLog[0]);
            int endTime = convertIntTime(splitLog[1]);

            for (int i = startTime; i < endTime; i++) {
                times[i]++;
            }
        }

        int maxTime = 0;

        long totalTime = 0;
        for (int i = 0; i < advTime; i++) {
            totalTime += times[i];
        }

        long maxTotalTime = totalTime;
        for (int i = advTime; i < playTime; i++) {
            totalTime += times[i] - times[i - advTime];

            if (totalTime > maxTotalTime) {
                maxTotalTime = totalTime;
                maxTime = i - advTime + 1;
            }
        }

        return convertStrTime(maxTime);
    }

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {
                "01:20:15-01:45:14",
                "00:40:31-01:00:00",
                "00:25:50-00:48:29",
                "01:30:59-01:53:29",
                "01:37:44-02:02:30"
        };

        System.out.println(solution(play_time, adv_time, logs));
    }
}

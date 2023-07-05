package programmers.kakao.주차요금계산;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {

    private static int makeMinute(String str) {
        String[] timeTable = str.split(":");
        return Integer.parseInt(timeTable[0]) * 60 + Integer.parseInt(timeTable[1]);
    }

    public static int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> totalTime = new HashMap<>();

        for (String record : records) {
            String[] temp = record.split(" ");
            int time = makeMinute(temp[0]);

            if (temp[2].equals("OUT")) {
                int useTime = time - map.get(temp[1]);

                if (totalTime.containsKey(temp[1])) {
                    useTime += totalTime.get(temp[1]);
                }

                totalTime.put(temp[1], useTime);
                map.remove(temp[1]);
                continue;
            }

            map.put(temp[1], time);
        }

        // 아직 안나간 차량 처리
        for (String carNum : map.keySet()) {
            Integer d = map.get(carNum);
            d = (d == null) ? 0 : d;
            int useTime = 1439 - d.intValue();

            Integer e = totalTime.get(carNum);
            e = (e == null) ? 0 : e;
            int sum = useTime + e.intValue();

            totalTime.put(carNum, sum);
        }

        List<String> keySet = new ArrayList<>(totalTime.keySet());
        Collections.sort(keySet);

        int[] answer = new int[keySet.size()];
        int idx = 0;

        for (String key : keySet) {
            int time = totalTime.get(key);

            if (time <= fees[0]) {
                time = fees[1];
            } else {
                time = fees[1] + (int) Math.ceil((double) (time - fees[0]) / fees[2]) * fees[3];
            }

            answer[idx++] = time;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] fees = { 180, 5000, 10, 600 };
        String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };

        System.out.println(Arrays.toString(solution(fees, records)));
    }
}
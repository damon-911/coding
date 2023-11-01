package programmers.string.추석트래픽;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static class Traffic {
        int start;
        int end;

        public Traffic(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int solution(String[] lines) {
        int answer = 1;

        List<Traffic> trafficList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            int endTime = (int) ((Integer.parseInt(lines[i].substring(11, 13)) * 3600
                    + Integer.parseInt(lines[i].substring(14, 16)) * 60) * 1000
                    + Double.parseDouble(lines[i].substring(17, 23)) * 1000);

            int processTime = (int) (Double.parseDouble(lines[i].substring(24, lines[i].length() - 1)) * 1000);
            int startTime = endTime - processTime + 1;
            trafficList.add(new Traffic(startTime, endTime));
        }

        int cnt;
        for (int i = 0; i < trafficList.size(); i++) {
            cnt = 1;

            for (int j = i + 1; j < trafficList.size(); j++) {
                if (trafficList.get(i).end + 1000 > trafficList.get(j).start)
                    cnt++;
            }

            if (answer < cnt)
                answer = cnt;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] lines = { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" };

        System.out.println(solution(lines));
    }
}
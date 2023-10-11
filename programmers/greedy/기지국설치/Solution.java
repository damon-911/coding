package programmers.greedy.기지국설치;

public class Solution {

    static int countStation(int start, int end, int w) {
        int result = (end - start + 1) / (2 * w + 1);
        if ((end - start + 1) % (2 * w + 1) > 0)
            result++;
        return result;
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int pos = 1;

        for (int i = 0; i < stations.length; i++) {
            if (pos < stations[i] - w)
                answer += countStation(pos, stations[i] - w - 1, w);
            pos = stations[i] + w + 1;
        }

        if (stations[stations.length - 1] + w < n)
            answer += countStation(stations[stations.length - 1] + w + 1, n, w);

        return answer;
    }

    public static void main(String[] args) {
        int N = 11;
        int[] stations = { 4, 11 };
        int W = 1;

        System.out.println(solution(N, stations, W));
    }
}
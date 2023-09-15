package programmers.hash.시소짝꿍;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        Map<Double, Integer> map = new HashMap<>();

        for (int weight : weights) {
            double a = (weight * 2.0) / 2.0;
            double b = (weight * 2.0) / 3.0;
            double c = (weight * 2.0) / 4.0;
            double d = (weight * 3.0) / 4.0;

            if (map.containsKey(a))
                answer += map.get(a);
            if (map.containsKey(b))
                answer += map.get(b);
            if (map.containsKey(c))
                answer += map.get(c);
            if (map.containsKey(d))
                answer += map.get(d);

            map.put((weight * 1.0), map.getOrDefault((weight * 1.0), 0) + 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] weights = { 100, 180, 360, 100, 270 };

        System.out.println(solution(weights));
    }
}
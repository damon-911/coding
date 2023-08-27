package programmers.data_structure.캐시;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0)
            return cities.length * 5;

        List<String> cache = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            if (cache.remove(city)) {
                answer += 1;
                cache.add(city);
            } else {
                answer += 5;
                if (cache.size() >= cacheSize)
                    cache.remove(0);
                cache.add(city);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };

        System.out.println(solution(cacheSize, cities));
    }
}
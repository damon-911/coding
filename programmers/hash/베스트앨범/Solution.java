package programmers.hash.베스트앨범;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {

    public static int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();

        HashMap<String, Integer> hashMap = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            hashMap.put(genres[i], hashMap.getOrDefault(genres[i], 0) + plays[i]);

            if (!music.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
            } else {
                music.get(genres[i]).put(i, plays[i]);
            }
        }

        List<String> keySet = new ArrayList<>(hashMap.keySet());
        Collections.sort(keySet, (s1, s2) -> hashMap.get(s2) - hashMap.get(s1));

        for (String key : keySet) {
            HashMap<Integer, Integer> map = music.get(key);

            List<Integer> genreList = new ArrayList<>(map.keySet());
            Collections.sort(genreList, (s1, s2) -> map.get(s2) - map.get(s1));

            answer.add(genreList.get(0));

            if (genreList.size() > 1)
                answer.add(genreList.get(1));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = { 500, 600, 150, 800, 2500 };

        System.out.println(Arrays.toString(solution(genres, plays)));
    }
}
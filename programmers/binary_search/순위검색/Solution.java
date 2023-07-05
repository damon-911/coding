package programmers.binary_search.순위검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {

    static HashMap<String, List<Integer>> map;

    public static void makeSentence(String[] arr, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(arr[4]));
            return;
        }

        makeSentence(arr, str + "-", cnt + 1);
        makeSentence(arr, str + arr[cnt], cnt + 1);
    }

    public static int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        map = new HashMap<String, List<Integer>>();

        for (int i = 0; i < info.length; i++) {
            String[] temp = info[i].split(" ");
            makeSentence(temp, "", 0);
        }

        for (String key : map.keySet())
            Collections.sort(map.get(key));

        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll(" and ", "");
            String[] qArr = q.split(" ");
            answer[i] = map.containsKey(qArr[0]) ? binarySearch(qArr[0], Integer.parseInt(qArr[1])) : 0;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        System.out.println(Arrays.toString(solution(info, query)));
    }
}
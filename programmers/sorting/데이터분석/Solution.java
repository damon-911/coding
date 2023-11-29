package programmers.sorting.데이터분석;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("code", 0);
        orderMap.put("date", 1);
        orderMap.put("maximum", 2);
        orderMap.put("remain", 3);

        int[][] answer = Arrays.stream(data).filter(x -> x[orderMap.get(ext)] < val_ext).toArray(int[][]::new);
        Arrays.sort(answer, (o1, o2) -> o1[orderMap.get(sort_by)] - o2[orderMap.get(sort_by)]);

        return answer;
    }

    public static void main(String[] args) {
        int[][] data = {
                { 1, 20300104, 100, 80 },
                { 2, 20300804, 847, 37 },
                { 3, 20300401, 10, 8 }
        };
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";

        int[][] result = solution(data, ext, val_ext, sort_by);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
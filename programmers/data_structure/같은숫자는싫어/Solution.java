package programmers.data_structure.같은숫자는싫어;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public static int[] solution(int[] arr) {
        int[] answer = {};

        ArrayList<Integer> arrList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || (i != 0 && arr[i - 1] != arr[i]))
                arrList.add(arr[i]);
        }

        answer = arrList.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 3, 3, 0, 1, 1 };

        System.out.println(Arrays.toString(solution(arr)));
    }
}
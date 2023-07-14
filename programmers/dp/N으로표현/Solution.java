package programmers.dp.N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static int solution(int N, int number) {
        List<Set<Integer>> countList = new ArrayList<>();

        for (int i = 0; i < 9; i++)
            countList.add(new HashSet<>());

        countList.get(1).add(N);

        for (int i = 2; i <= 8; i++) {
            Set<Integer> countSet = countList.get(i);

            for (int j = 1; j <= i; j++) {
                Set<Integer> preSet = countList.get(j);
                Set<Integer> postSet = countList.get(i - j);

                for (int preNum : preSet) {
                    for (int postNum : postSet) {
                        countSet.add(preNum + postNum);
                        countSet.add(preNum - postNum);
                        countSet.add(preNum * postNum);

                        if (preNum != 0 && postNum != 0)
                            countSet.add(preNum / postNum);
                    }
                }

                countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
        }

        for (Set<Integer> set : countList) {
            if (set.contains(number))
                return countList.indexOf(set);
        }

        return -1;
    }

    public static void main(String[] args) {
        int N = 5;
        int number = 12;

        System.out.println(solution(N, number));
    }
}
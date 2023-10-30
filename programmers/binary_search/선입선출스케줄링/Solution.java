package programmers.binary_search.선입선출스케줄링;

public class Solution {

    static int countWork(int time, int[] cores) {
        if (time == 0) { // 시간이 0일 때, 처리할 수 있는 작업량은 코어의 개수
            return cores.length;
        }

        int count = cores.length;
        for (int i = 0; i < cores.length; i++) { // time까지 코어가 처리할 수 있는 작업량 합산
            count += (time / cores[i]);
        }

        return count;
    }

    public static int solution(int n, int[] cores) {
        int answer = 0;

        int min = 0;
        int max = cores[0] * n;

        int time = 0;
        int workCnt = 0; // time까지 처리한 작업량

        while (min <= max) {
            int mid = (min + max) / 2;

            int count = countWork(mid, cores);

            if (count >= n) { // 해당 시간까지 처리한 작업량보다 n이 크면 time과 workCnt 갱신
                max = mid - 1;
                time = mid;
                workCnt = count;
            } else {
                min = mid + 1;
            }
        }

        workCnt -= n; // 처리한 작업량과 n의 차이 갱신
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) { // 시간이 time일때, 작업을 처리하는 core 구하기
                if (workCnt == 0) {
                    answer = i + 1;
                    break;
                }
                workCnt--;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] cores = { 1, 2, 3 };

        System.out.println(solution(n, cores));
    }
}
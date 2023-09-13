package programmers.array.마법의엘리베이터;

public class Solution {

    public static int solution(int storey) {
        int answer = 0;

        String temp = Integer.toString(storey);
        int[] arr = new int[temp.length()];

        for (int i = 0; i < temp.length(); i++) {
            arr[i] = temp.charAt(i) - '0';
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > 5) {
                answer += 10 - arr[i];

                if (i == 0)
                    answer++;
                else
                    arr[i - 1]++;
            } else if (i > 0 && arr[i] == 5 && arr[i - 1] >= 5) {
                arr[i - 1]++;
                answer += 5;
            } else {
                answer += arr[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int storey = 16;

        System.out.println(solution(storey));
    }
}
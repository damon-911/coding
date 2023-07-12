package programmers.simulation.조이스틱;

public class Solution {

    public static int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 오른쪽으로 쭉 간 횟수
        int cur = 0;

        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            cur = i + 1;

            // 연속되는 'A' 갯수 확인
            while (cur < name.length() && name.charAt(cur) == 'A')
                cur++;

            // 오른쪽으로 갔다 다시 왼쪽으로 꺾기
            move = Math.min(move, i * 2 + (name.length() - cur));

            // 왼쪽으로 갔다 다시 오른쪽으로 꺾기
            move = Math.min(move, (name.length() - cur) * 2 + i);
        }

        return answer + move;
    }

    public static void main(String[] args) {
        String name = "JEROEN";

        System.out.println(solution(name));
    }
}
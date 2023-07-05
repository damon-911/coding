package programmers.simulation.스킬트리;

public class Solution {

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String str : skill_trees) {
            str = str.replaceAll("[^" + skill + "]", "");
            answer += (skill.indexOf(str) == 0) ? 1 : 0;
        }

        return answer;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };

        System.out.println(solution(skill, skill_trees));
    }
}
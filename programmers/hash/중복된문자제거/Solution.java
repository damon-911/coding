package programmers.hash.중복된문자제거;

public class Solution {

    public static String solution(String my_string) {
        String answer = "";

        for (int i = 0; i < my_string.length(); i++) {
            if (my_string.indexOf(my_string.charAt(i)) == i)
                answer += my_string.charAt(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String my_string = "people";

        System.out.println(solution(my_string));
    }
}
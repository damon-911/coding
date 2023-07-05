package programmers.string.숫자문자열과영단어;

public class Solution {

    public static int solution(String s) {
        String[] nums = new String[] { "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine" };
        String str = s;

        for (int i = 0; i < 10; i++) {
            str = str.replace(nums[i], Integer.toString(i));
        }

        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        String s = "one4seveneight";

        System.out.println(solution(s));
    }
}
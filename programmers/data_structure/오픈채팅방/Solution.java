package programmers.data_structure.오픈채팅방;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static String[] solution(String[] record) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (String str : record) {
            String[] command = str.split(" ");

            if (command[0].equals("Enter")) {
                list.add(command[1] + "님이 들어왔습니다.");
                map.put(command[1], command[2]);
            } else if (command[0].equals("Change")) {
                map.put(command[1], command[2]);
            } else {
                list.add(command[1] + "님이 나갔습니다.");
            }
        }

        String[] answer = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            int idx = list.get(i).indexOf("님");
            String id = list.get(i).substring(0, idx);
            String[] temp = list.get(i).split(" ");
            answer[i] = map.get(id) + "님이 " + temp[1];
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
        };

        System.out.println(Arrays.toString(solution(record)));
    }
}
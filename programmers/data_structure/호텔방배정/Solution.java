package programmers.data_structure.호텔방배정;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static Map<Long, Long> map = new HashMap<>();

    private static long findEmptyRoom(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num + 1);
            return num;
        }

        long nextRoom = map.get(num);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(num, emptyRoom);

        return emptyRoom;
    }

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        long k = 10;
        long[] room_number = { 1, 3, 4, 1, 3, 1 };

        System.out.println(Arrays.toString(solution(k, room_number)));
    }
}
package programmers.string.방금그곡;

public class Solution {

    static String changeMelody(String str) {
        String newMelody = str.replaceAll("C#", "H");
        newMelody = newMelody.replaceAll("D#", "I");
        newMelody = newMelody.replaceAll("F#", "J");
        newMelody = newMelody.replaceAll("G#", "K");
        newMelody = newMelody.replaceAll("A#", "L");
        return newMelody;
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        int maxPlayTime = -1;

        // 멜로디에 들어있는 # 붙은 음 치환
        m = changeMelody(m);

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");

            String startH = info[0].split(":")[0];
            String startM = info[0].split(":")[1];
            String endH = info[1].split(":")[0];
            String endM = info[1].split(":")[1];

            // 총 재생 시간 구하기
            int time = 60 * (Integer.parseInt(endH) - Integer.parseInt(startH));
            time += Integer.parseInt(endM) - Integer.parseInt(startM);

            // 멜로디에 들어있는 # 붙은 음 치환
            String music = changeMelody(info[3]);

            // 재생 시간만큼 들린 노래 악보 만들기
            String heard = music.repeat(time / music.length());
            heard += music.substring(0, time % music.length());

            // 재생된 시간이 제일 긴 음악 제목 반환
            if (heard.contains(m)) {
                if (time > maxPlayTime) {
                    answer = info[2];
                    maxPlayTime = time;
                }
            }
        }

        return answer.equals("") ? "(None)" : answer;
    }

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };

        System.out.println(solution(m, musicinfos));
    }
}
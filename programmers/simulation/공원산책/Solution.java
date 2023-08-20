package programmers.simulation.공원산책;

public class Solution {

    public static int[] solution(String[] park, String[] routes) {
        int curX = 0;
        int curY = 0;

        char[][] map = new char[park.length][park[0].length()];

        for (int i = 0; i < park.length; i++) {
            map[i] = park[i].toCharArray();

            if (park[i].contains("S")) {
                curX = i;
                curY = park[i].indexOf("S");
            }
        }

        for (String route : routes) {
            String[] command = route.split(" ");

            int tx = curX;
            int ty = curY;

            for (int i = 0; i < Integer.parseInt(command[1]); i++) {
                switch (command[0]) {
                    case "N":
                        tx--;
                        break;
                    case "S":
                        tx++;
                        break;
                    case "W":
                        ty--;
                        break;
                    case "E":
                        ty++;
                        break;
                    default:
                        break;
                }

                if (tx >= 0 && ty >= 0 && tx < map.length && ty < map[0].length) {
                    if (map[tx][ty] == 'X')
                        break;

                    // 지금까지 조건에 다 만족했다면
                    if (i == Integer.parseInt(command[1]) - 1) {
                        curX = tx;
                        curY = ty;
                    }
                }
            }
        }

        return new int[] { curX, curY };
    }

    public static void main(String[] args) {
        String[] park = { "SOO", "OOO", "OOO" };
        String[] routes = { "E 2", "S 2", "W 1" };

        System.out.println(solution(park, routes));
    }
}
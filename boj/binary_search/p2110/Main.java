package boj.binary_search.p2110;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static int[] house;

    private static int canInstall(int distance) {
        // 첫 번째 집은 무조건 설치한다고 가정
        int count = 1;
        int lastLocation = house[0];

        for (int i = 1; i < house.length; i++) {
            int cur = house[i];

            /*
             * 현재 집의 위치와 직전에 설치했던 집의 위치 간 거리가
             * 최소 거리(distance)보다 크거나 같을 때 공유기 설치 개수를 늘려주고
             * 마지막 설치 위치를 갱신해준다.
             */
            if (cur - lastLocation >= distance) {
                count++;
                lastLocation = cur;
            }
        }

        return count;
    }

    private static void install() {
        int low = 1;
        int high = house[N - 1] - house[0] + 1;

        while (low < high) {
            int mid = (low + high) / 2;

            /*
             * mid 거리에 대해 설치 가능한 공유기 개수가 C개에 못 미치면
             * 거리를 좁혀야 하기 때문에 high를 줄인다.
             * 
             * 설치 가능한 공유기 개수가 C개보다 크거나 같으면
             * low를 늘려 거리를 벌린다.
             * 
             * 해당 과정을 반복하면서 최소 거리가 가질 수 있는 최대 거리를 찾는다.
             */
            if (canInstall(mid) < C) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(low - 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/binary_search/p2110/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        install();
    }
}
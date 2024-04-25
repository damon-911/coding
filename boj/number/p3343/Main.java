package boj.number.p3343;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p3343/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());

        long cntA = Long.parseLong(st.nextToken());
        long priceA = Long.parseLong(st.nextToken());
        long cntB = Long.parseLong(st.nextToken());
        long priceB = Long.parseLong(st.nextToken());

        // 가성비 좋은 세트를 A에 배치
        // A를 B개 구매한 가격 < B를 A개 구매한 가격
        if (cntA * priceB < cntB * priceA) {
            long cntTemp = cntA;
            cntA = cntB;
            cntB = cntTemp;

            long priceTemp = priceA;
            priceA = priceB;
            priceB = priceTemp;
        }

        long result = Long.MAX_VALUE;

        // 가성비 나쁜 세트를 하나씩 늘려가면서 체크
        // B를 A개 이상 구매할 필요가 없음
        for (int b = 0; b < cntA; b++) {
            long a = (long) Math.ceil((double) (N - b * cntB) / cntA);

            boolean isOver = false;
            if (a < 0) {
                a = 0;
                isOver = true;
            }

            result = Math.min(result, a * priceA + b * priceB);

            if (isOver) {
                break;
            }
        }

        System.out.println(result);
    }
}

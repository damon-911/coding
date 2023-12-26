package boj.number.p5376;

import java.io.*;

public class Main {

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p5376/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            if (!str.contains("(")) {
                int tens = (int) Math.pow(10, str.length() - 2);
                int num = Integer.parseInt(str.substring(2, str.length()));
                long gcdResult = gcd(tens, num);
                System.out.println(Long.toString(num / gcdResult) + "/" + Long.toString(tens / gcdResult));
            } else {
                int index = str.indexOf("(");
                int len = str.length() - 2;
                long tens = (long) Math.pow(10, len - 2) - (long) Math.pow(10, index - 2);

                String numStr = str.substring(2, str.length() - 1);
                numStr = numStr.replace("(", "");
                numStr = numStr.replace(")", "");
                long num = Long.parseLong(numStr);

                if (!str.substring(2, index).equals("")) {
                    num -= Long.parseLong(str.substring(2, index));
                }

                long gcdResult = gcd(tens, num);
                System.out.println(Long.toString(num / gcdResult) + "/" + Long.toString(tens / gcdResult));
            }
        }
    }
}
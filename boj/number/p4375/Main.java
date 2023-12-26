package boj.number.p4375;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/number/p4375/input.txt"));
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            int mod = 0;

            for (int i = 1;; i++) {
                mod = (mod * 10 + 1) % num;
                if (mod == 0) {
                    System.out.println(i);
                    break;
                }
            }
        }

        scanner.close();
    }
}

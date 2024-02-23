package boj.data_structure.p4358;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/data_structure/p4358/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> trees = new TreeMap<>();
        int size = 0;

        while (br.ready()) {
            String name = br.readLine();
            trees.put(name, trees.getOrDefault(name, 0) + 1);
            size++;
        }

        for (Entry<String, Integer> entry : trees.entrySet()) {
            System.out.printf("%s %.4f\n", entry.getKey(), (double) entry.getValue() * 100 / size);
        }
    }
}

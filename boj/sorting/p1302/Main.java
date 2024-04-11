package boj.sorting.p1302;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("boj/sorting/p1302/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Integer> map = new HashMap<>();

		int max = 0;
		for (int i = 0; i < N; i++) {
			String book = br.readLine();
			map.put(book, map.getOrDefault(book, 0) + 1);
			max = Math.max(max, map.get(book));
		}

		List<String> list = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				list.add(entry.getKey());
			}
		}
		Collections.sort(list);

		System.out.println(list.get(0));
	}
}

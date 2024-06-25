package boj.sorting.p1092;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("boj/sorting/p1092/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Integer> crane = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());
		List<Integer> box = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}

		crane.sort(Collections.reverseOrder());
		box.sort(Collections.reverseOrder());

		if (crane.get(0) < box.get(0)) {
			System.out.println(-1);
			return;
		}

		int time = 0;

		while (!box.isEmpty()) {
			int craneIdx = 0;
			int boxIdx = 0;

			while (craneIdx < N) {
				if (boxIdx == box.size()) {
					break;
				} else if (box.get(boxIdx) <= crane.get(craneIdx)) {
					box.remove(boxIdx);
					craneIdx++;
				} else {
					boxIdx++;
				}
			}

			time++;
		}

		System.out.println(time);
	}
}

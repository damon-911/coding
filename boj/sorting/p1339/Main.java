package boj.sorting.p1339;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] words;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p1339/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // 알파벳은 총 26개
        words = new int[26];
        
        for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < str.length(); j++) {
             	// 자릿수만큼 10의 거듭제곱 더해준다
				words[str.charAt(j) - 'A'] += (Math.pow(10, str.length() - j - 1));
			}
		}
		
		Arrays.sort(words);
		
		int answer = 0;
		int num = 9;
		

		for (int i = 25; i >= 0; i--) {
			if (words[i] == 0) {
				continue;
			}
			
			 // 큰 수부터 9, 8, 7, ... 순서대로 붙여준다
			answer += (words[i] * num); 
			num--;
		}
		
		System.out.println(answer);
    }
}

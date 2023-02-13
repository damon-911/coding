package boj.dp.p14003;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums, trace;
    static List<Integer> list;

    static void findLongest() {
        for (int i = 1; i <= N; i++) {
            // 현재 체크하는 숫자가 리스트의 마지막 수보다 큰 경우 리스트에 추가해준다
            if (list.get(list.size() - 1) < nums[i]) {
                list.add(nums[i]);
                trace[i] = list.size() - 1;
            }
            else {
                // 현재 체크하는 숫자가 리스트의 마지막 수보다 작은 경우
                // 이분 탐색 시작
                int left = 1;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    // Lower Bound (중간 값이 작으면 시작 위치를 중간 다음 위치로, 크거나 같으면 끝 위치를 중간 위치로 이동시킨다)
                    if (list.get(mid) < nums[i]) {
                        left = mid + 1;
                    }
                    else {
                        right = mid;
                    }
                }

                // LIS 리스트를 갱신해주고 위치를 기록한다
                list.set(right, nums[i]);
                trace[i] = right;
            }
        }

        int longest = list.size() - 1;
        System.out.println(longest);

        // 역추적 경로를 저장할 stack
        Stack<Integer> stack = new Stack<>();
        for(int i = N; i > 0; i--) {
            // 찾는 인덱스와 같은 경우
            if (trace[i] == longest) {
                longest--;
                stack.push(nums[i]);
            }
        }

        // 스택에서 꺼내며 최장 증가 수열을 출력한다
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/dp/p14003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];
        trace = new int[N + 1];

        list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            trace[i] = -1;
        }

        findLongest();
    }
}

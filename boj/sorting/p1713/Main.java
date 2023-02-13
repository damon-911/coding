package boj.sorting.p1713;

import java.io.*;
import java.util.*;

public class Main {
    static int N, total;

    static class Student implements Comparable<Student> {
        int id;
        int count;
        int timestamp;
        boolean isIn;

        public Student(int id, int count, int timestamp, boolean isIn) {
            this.id = id;
            this.count = count;
            this.timestamp = timestamp;
            this.isIn = isIn;
        }

        @Override
        public int compareTo(Student s) {
            int comp = this.count - s.count;
            if (comp == 0) {
                return this.timestamp - s.timestamp;
            }
            return comp;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("boj/sorting/p1713/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        total = Integer.parseInt(br.readLine());

        Student[] students = new Student[101];  // 1 ~ 100
        List<Student> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= total; i++) {
            int id = Integer.parseInt(st.nextToken());

            // 처음으로 추천받은 학생이라면
            if(students[id] == null) {
                students[id] = new Student(id, 0, 0, false);
            }

            // 이미 게시되어 있는 경우
            if(students[id].isIn) {
                students[id].count++;
            }
            // 게시되어 있지 않은 경우
            else {
                // 게시판이 꽉 찬 경우
                if (list.size()==N) {
                    Collections.sort(list);
                    list.get(0).isIn = false;
                    list.remove(0);
                }

                students[id].count = 1;
                students[id].timestamp = i;
                students[id].isIn = true;
                list.add(students[id]);
            }
        }

        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id - o2.id;
            }
        });

        for (Student student : list) {
            System.out.print(student.id + " ");
        }
    }
}

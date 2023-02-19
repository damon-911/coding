package boj.sorting;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortSample {

    static class Item implements Comparable<Item> {
        int value1;
        int value2;

        public Item(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public String toString() {
            return "{" + value1 +
                    ", " + value2 +
                    '}';
        }

        @Override
        public int compareTo(Item item) {
            int comp = this.value1 - item.value1;
            if (comp == 0) {
                return this.value2 - item.value2;
            }
            return comp;
        }
    }

    public static void main(String[] args) {
        // 객체 타입 정렬
        Integer[] nums = { 1, 3, 5, 2, 4 };

        System.out.println(Arrays.toString(nums));

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(nums));

        Arrays.sort(nums, Comparator.reverseOrder());
        System.out.println(Arrays.toString(nums));

        String[] arr = { "banana", "orange", "apple", "melon" };

        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));


        // ArrayList 정렬
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(1);
        list.add(3);

        for (Integer element : list) {
            System.out.print(element + " ");
        }
        System.out.println();

        Collections.sort(list);

        for (Integer element : list) {
            System.out.print(element + " ");
        }
        System.out.println();


        // 사용자 정의 객체 정렬
        Item[] items = new Item[5];
        items[0] = new Item(3, 5);
        items[1] = new Item(1, 4);
        items[2] = new Item(4, 2);
        items[3] = new Item(2, 3);
        items[4] = new Item(3, 2);

        System.out.println(Arrays.toString(items));

        Arrays.sort(items);
        System.out.println(Arrays.toString(items));

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                int comp = item2.value1 - item1.value1;
                if (comp == 0) {
                    return item2.value2 - item1.value2;
                }
                return comp;
            }
        });
        System.out.println(Arrays.toString(items));
    }
}

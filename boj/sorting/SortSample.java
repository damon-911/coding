package boj.sorting;

import java.util.Arrays;
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
        public int compareTo(Item o2) {
            int comp = value1 - o2.value1;
            if (comp == 0) {
                return value2 - o2.value2;
            }
            return comp;
        }
    }

    public static void main(String[] args) {
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


        Item[] items = new Item[5];
        items[0] = new Item(4, 2);
        items[1] = new Item(1, 4);
        items[2] = new Item(3, 5);
        items[3] = new Item(2, 3);
        items[4] = new Item(3, 2);

        System.out.println(Arrays.toString(items));

        Arrays.sort(items);

        System.out.println(Arrays.toString(items));

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int comp1 = o1.value1 - o2.value1;
                if (comp1 == 0) {
                    return o1.value2 - o2.value2;
                }
                return comp1;
            }
        });

        System.out.println(Arrays.toString(items));
    }
}

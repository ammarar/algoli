package me.algoli.sort;

import me.algoli.list.List;

/**
 * Created by aalrashe on 4/8/2016.
 */
public class ThreeWayQuickSort implements SortStrategy {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, 0, list.size()-1);
    }

    private <T extends Comparable<? super T>> void sort(List<T> list, int start, int end) {
        if (end >= start) return;
        int less = start;
        int greater = end;
        T v = list.get(start);
        int i = start;
        while (i <= greater) {
            if (list.get(i).compareTo(v) < 0) {
                list.exchange(less, i);
                less++; i++;
            } else if (list.get(i).compareTo(v) > 0) {
                list.exchange(i, greater);
                greater--;
            } else {
                i++;
            }
        }

        sort(list, start, less - 1);
        sort(list, greater + 1, end);
    }
}

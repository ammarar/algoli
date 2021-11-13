package me.algoli.sort;

import me.algoli.list.List;

public interface SortStrategy {

    <T extends Comparable<? super T>> void sort(List<T> list);

    static <T extends Comparable<? super T>>  boolean isSorted(List<T> list) {
        if (list == null || list.size() < 2) return true;
        T prev = list.get(0);
        for (int i=1; i<list.size(); i++) {
            if (prev.compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    static <T extends Comparable<? super T>>  boolean needsSorting(List<T> list) {
        return list != null && list.size() > 1;
    }
}

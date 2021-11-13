package me.algoli.sort;

import me.algoli.list.List;

public class InsertionSort implements SortStrategy {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        for (int i=1; i<list.size(); i++) {
            for (int j=i-1; j>=0 && list.get(i).compareTo(list.get(j)) < 0; j--) {
                list.exchange(i, j);
            }
        }
    }
}

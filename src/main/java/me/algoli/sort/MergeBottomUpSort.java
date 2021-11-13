package me.algoli.sort;

import me.algoli.list.ArrayList;
import me.algoli.list.List;

public class MergeBottomUpSort extends MergeSort {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        List<T> aux = ArrayList.create(list.size());
        for (int i=1; i<list.size(); i *= 2) {
            for (int j=0; j<list.size() - i; j += 2*i) {
                merge(list, j, j + i - 1, Math.min(j + i + i - 1, list.size() - 1), aux);
            }
        }
    }
}

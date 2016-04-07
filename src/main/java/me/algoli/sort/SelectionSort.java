package me.algoli.sort;

import me.algoli.list.List;

/**
 * Created by ammar on 2/22/16.
 */
public class SelectionSort implements SortStrategy{

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (list == null || list.size() < 2) return;
        for (int i=0; i<list.size()-1; i++) {
            int min = i;
            for (int j=i+1; j<list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            list.exchange(i, min);
        }
    }
}

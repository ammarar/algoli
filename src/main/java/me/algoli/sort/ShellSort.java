package me.algoli.sort;

import me.algoli.list.List;

/**
 * Created by ammar on 2/22/16.
 */
public class ShellSort implements SortStrategy {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        int h = 1;
        while (h < list.size()/3) { h = 3 * h + 1; }

        while (h >= 1) {
            for (int i = h; i<list.size(); i++) {
                for (int j = i; j >= h && list.get(j).compareTo(list.get(j - h)) < 0; j -= h) {
                    list.exchange(j, j - h);
                }
            }
            h = h/3;
        }
    }
}

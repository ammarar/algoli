package me.algoli.sort;

import me.algoli.list.List;

/**
 * Created by ammar on 2/22/16.
 */
public interface SortStrategy {

    <T> void sort(List<Comparable<T>> list);
}

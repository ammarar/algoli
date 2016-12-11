package me.algoli.tuple;

/**
 * Created by ammar on 12/9/16.
 */
public class Tuple<L,R> {

    private L left;
    private R right;

    private Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L,R> Tuple<L, R> of(L left, R right) {
        return new Tuple<>(left, right);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}

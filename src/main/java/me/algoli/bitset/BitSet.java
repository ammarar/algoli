package me.algoli.bitset;

import java.util.Arrays;

public class BitSet {

    private static final int UNIT_SIZE = 64;
    long[] values = null;
    int usedLength = 0;
    private enum Op {
        AND, OR, XOR
    }

    private BitSet() {
        this.values = new long[1];
        usedLength = 1;
    }

    private BitSet(int nbits) {
        int numberOfUnits = (int) Math.ceil((double)nbits/UNIT_SIZE);
        values = new long[numberOfUnits];
        usedLength = numberOfUnits;
    }

    private BitSet(long[] newVals, int length) {
        this.values = Arrays.copyOf(newVals, length);
        this.usedLength = length;
    }

    public static BitSet create() {
        return new BitSet();
    }

    public static BitSet create(int nbits) {
        return new BitSet(nbits);
    }

    public static BitSet create(long[] newVals, int length) {
        return new BitSet(newVals, length);
    }

    public BitSet and(BitSet set) {
        long[] andOpResult = operate(set.getValues(), Op.AND);
        return BitSet.create(andOpResult, andOpResult.length);
    }

    public BitSet or(BitSet set) {
        long[] andOpResult = operate(set.getValues(), Op.OR);
        return BitSet.create(andOpResult, andOpResult.length);
    }

    public BitSet xor(BitSet set) {
        long[] andOpResult = operate(set.getValues(), Op.XOR);
        return BitSet.create(andOpResult, andOpResult.length);
    }

    private long[] operate(long[] thatVal, Op op) {
        long[] longer = thatVal;
        long[] shorter = getValues();
        if (this.getValues().length > thatVal.length) {
            longer = getValues();
            shorter = thatVal;
        }

        for (int i=0; i < shorter.length; i++) {
            switch (op) {
                case AND:
                    longer[i] &= shorter[i];
                    break;
                case OR:
                    longer[i] |= shorter[i];
                    break;
                case XOR:
                    longer[i] ^= shorter[i];
                    break;
            }
        }
        return longer;
    }

    public BitSet clear(int index) {
        return set(index, false);
    }

    public BitSet clear(int from, int to) {
        return set(from, to, false);
    }

    public BitSet toggle(int index) {
        boolean val = get(index);
        return set(index, !val);
    }

    public BitSet toggle(int from, int to) {
        enforceIndex(from);
        enforceIndex(to);
        long[] values = getValues();
        for (int i = from; i <= to; i++) {
            setBit(i, !getBit(i, values), values);
        }
        return create(values, values.length);
    }

    public boolean get(int index) {
        enforceIndex(index);
        updateUsedLength(index);
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        boolean result = ((values[arrayIndex] >> indexInside) & 1) == 1;
        return result;
    }

    public BitSet get(int from, int to) {
        return null;
    }

    public BitSet set(int index) {
        return set(index, true);
    }

    public BitSet set(int index, boolean value) {
        enforceIndex(index);
        long[] newVals = Arrays.copyOf(values, values.length);
        setBit(index, value, newVals);
        return create(newVals, usedLength);
    }

    private void enforceIndex(int index) {
        assert index >= 0 : String.format("index [%s] has to be larger than or equal to 0", index);
        assert index <= size() : String.format("index [%s] is greater than the size [%s]", index, size());
    }

    private void updateUsedLength(int newLength) {
        if (newLength > usedLength) {
            usedLength = newLength;
        }
    }

    public BitSet set(int from, int to, boolean value) {
        if (from > to) {
            throw new IllegalArgumentException(String.format("from=[%s] is greater than to=[%]", from, to));
        }
        enforceIndex(from);
        enforceIndex(to);
        long[] newVals = getValues();
        for (int index = from; index <= to; index++) {
            setBit(index, value, newVals);
        }
        return create(values, values.length);
    }

    private static void setBit(int index, boolean value, long[] values) {
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        if (value) {
            values[arrayIndex] = values[arrayIndex] | (1 << indexInside);
        } else {
            values[arrayIndex] = values[arrayIndex] & ~(1 << indexInside);
        }
    }

    private static boolean getBit(int index, long[] values) {
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        boolean result = ((values[arrayIndex] >> indexInside) & 1) == 1;
        return result;
    }

    public int length() {
        return usedLength;
    }

    public int size() {
        return values.length * UNIT_SIZE;
    }

    public long[] getValues() {
        return Arrays.copyOf(values, usedLength);
    }

    public BitSet complement() {
        long[] values = getValues();
        for (int i = 0; i < values.length; i++)
        {
            values[i] = ~values[i];
        }
        return create(values, values.length);
    }
}

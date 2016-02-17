package me.algoli.bitset;

import java.util.Arrays;

public class BitSet {

    private final int UNIT_SIZE = 64;
    long[] values = null;
    int usedLength = 0;
    private enum Op {
        AND, OR, XOR
    }

    private BitSet() {
        this.values = new long[1];
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
        return null;
    }

    public BitSet toggle(int index) {
        boolean val = get(index);
        return set(index, !val);
    }

    public BitSet toggle(int from, int to) {
        return null;
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
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        long[] newVals = Arrays.copyOf(values, values.length);
        int val = value? 1 : 0;
        newVals[arrayIndex] = newVals[arrayIndex] | (val << indexInside);
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
        return null;
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
}

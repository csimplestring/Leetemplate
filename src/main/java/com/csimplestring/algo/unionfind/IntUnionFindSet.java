package com.csimplestring.algo.unionfind;

/**
 * {@link IntUnionFindSet} is designed for integer type element when dealing with union find problems.
 * It uses an int array internally to speed up the parent-lookup.
 * <p>
 * If you are looking for a generic usage, @see {@link GenericUnionFindSet}
 */
public class IntUnionFindSet {
    private int rootNum;
    private int[] parents;

    public IntUnionFindSet(int rootNum, int[] parents) {
        this.rootNum = rootNum;
        this.parents = parents;
    }

    public int find(int element) {
        int x = element;
        while (x != this.parents[x]) {
            this.parents[x] = this.parents[this.parents[x]];
            x = this.parents[x];
        }

        return x;
    }

    public void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);
        if (p1 == p2) {
            return;
        }

        this.rootNum--;
        this.parents[p1] = p2;
    }

    public int getRootNum() {
        return rootNum;
    }
}

package com.csimplestring.algo.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Union Find Set is a data structure that tracks a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 * This implementation supports a more generic typed elements.
 * <p>
 * If you are looking for a performant solution, maybe look at UnionFindIntegerSet for integer only.
 *
 * @param <T>
 * @see https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
public class GenericUnionFindSet<T> {

    private int rootNum;
    private Map<T, T> parents;

    /**
     * initialize with a list of elements. In the beginning, the parent of each element is itself.
     *
     * @param elements
     */
    public GenericUnionFindSet(List<T> elements) {
        this.rootNum = elements.size();
        this.parents = new HashMap<>();
        elements.forEach(e -> this.parents.put(e, e));
    }

    /**
     * initialize with empty data.
     */
    public GenericUnionFindSet() {
        this.rootNum = 0;
        this.parents = new HashMap<>();
    }

    /**
     * Add one element into the set. If it does not have a parent, make its parent is itself, and rootNum plus one.
     *
     * @param element
     */
    public void add(T element) {
        T parent = find(element);

        if (parent == null) {
            this.parents.put(element, element);
            this.rootNum++;
            return;
        }

        this.parents.put(element, parent);
    }

    /**
     * Find the root of a given element.
     *
     * @param element
     * @return the root of element or null if no such element found.
     */
    public T find(final T element) {
        if (!this.parents.containsKey(element)) {
            return null;
        }

        T x = element;
        while (!x.equals(this.parents.get(x))) {
            // path compression
            T grand = this.parents.get(this.parents.get(x));
            this.parents.put(x, grand);
            // if path compression is hard to understand, just remember this line
            x = this.parents.get(x);
        }

        return x;
    }

    /**
     * Union t1 and t2, so they are pointed to the same root element.
     *
     * @param t1
     * @param t2
     */
    public void union(T t1, T t2) {
        if (isConnected(t1, t2)) {
            return;
        }

        this.rootNum--;
        T p1 = find(t1);
        T p2 = find(t2);
        this.parents.put(p1, p2);
    }

    /**
     * Check if t1 and t2 belongs to the same root.
     *
     * @param t1
     * @param t2
     * @return true if they belong to the same root otherwise false.
     */
    public boolean isConnected(T t1, T t2) {
        return find(t1).equals(find(t2));
    }

    /**
     * get the number of root elements.
     *
     * @return
     */
    public int getRootNum() {
        return rootNum;
    }
}
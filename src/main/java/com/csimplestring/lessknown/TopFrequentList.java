package com.csimplestring.lessknown;

import java.util.*;

/**
 * Top Frequency List, get the top K frequent elements out of N elements in O(N*log(K)) time, using O(N) space.
 * A min heap(PriorityQueue) internally is used to maintain the top K frequent elements.
 *
 * @param <T>
 */
public class TopFrequentList<T> {

    private final Map<T, Integer> counter = new HashMap<>();

    /**
     * Add element
     *
     * @param t element must support hashCode() and must not be NULL
     */
    public void add(T t) {
        counter.put(t, counter.getOrDefault(t, 0) + 1);
    }

    /**
     * Add all elements in l
     *
     * @param l the list which must not contain NULL element.
     */
    public void addAll(List<T> l) {
        l.forEach(this::add);
    }

    /**
     * Get the top K frequent elements as a list.
     *
     * @param topK     the top k frequency threshold
     * @param resolver the resolver to solve in case of tie.
     * @return the top K frequent elements
     */
    public List<T> findTopK(int topK, TieResolver<T> resolver) {
        PriorityQueue<T> queue = new PriorityQueue<>(topK, (o1, o2) -> {
            int freq1 = counter.get(o1);
            int freq2 = counter.get(o2);
            if (freq1 == freq2) {
                return resolver.resolve(freq1, o1, o2);
            }
            return freq1 - freq2;
        });

        for (T element : counter.keySet()) {
            queue.add(element);
            if (queue.size() > topK) {
                queue.poll();
            }
        }

        List<T> res = new ArrayList<>(topK);
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * Resolve the tie in case of having the same frequency.
     *
     * @param <T>
     */
    public interface TieResolver<T> {
        int resolve(int frequency, T o1, T o2);
    }
}

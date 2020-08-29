package com.csimplestring.algo.queue;

import java.util.ArrayDeque;
import java.util.Comparator;

/**
 * Like the {@link java.util.PriorityQueue},
 * MonotonicQueue keeps the internal elements in a monotonic increasing/decreasing order,
 * which is defined by passed comparator. However, such a monotonic increasing/decreasing order is maintained
 * by comparing+deleting the internal elements. While the {@link java.util.PriorityQueue} internally keeps all the elements,
 * its monotonic order is maintained by sorting the balanced tree.
 * <p>
 * The usage of MonotonicQueue:
 * - given a sliding window whose size is k, the MonotonicQueue can find the max/min element in O(k).
 *
 * @param <T> the element type
 * @see {https://en.wikipedia.org/wiki/Monotone_priority_queue}
 */
class MonotonicQueue<T> {
    private final ArrayDeque<T> deque;
    private final Comparator<T> comparator;

    /**
     * Constructor.
     *
     * @param capacity   the init capacity
     * @param comparator the comparator which maintains the order
     */
    public MonotonicQueue(int capacity, Comparator<T> comparator) {
        this.deque = new ArrayDeque<>(capacity);
        this.comparator = comparator;
    }

    /**
     * Size of the MonotonicQueue.
     *
     * @return Size of the MonotonicQueue.
     */
    public int size() {
        return deque.size();
    }

    /**
     * Max value that the queue has seen so far.
     *
     * @return Max value that the queue has seen so far.
     */
    public T max() {
        return deque.peek();
    }

    /**
     * Append an element at the tail of queue.
     *
     * @param n the element to be added.
     */
    public void add(T n) {
        while (!deque.isEmpty() && comparator.compare(deque.getLast(), n) < 0) {
            deque.removeLast();
        }
        deque.add(n);
    }

    /**
     * Remove the given element from the head of queue.
     *
     * @param n the element to be removed from the head.
     */
    public void pop(T n) {
        if (!deque.isEmpty() && comparator.compare(deque.peek(), n) == 0) {
            deque.pop();
        }
    }

    /**
     * For debug purpose, it shows all the internal elements of the queue.
     */
    @Override
    public String toString() {
        return deque.toString();
    }
}

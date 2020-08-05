package com.csimplestring.algo.array;

import java.util.Comparator;

/**
 * Binary Search Array template.
 *
 * @param <T>
 */
public class BinarySearchArray<T> {

    private Comparator<T> comparator;

    /**
     * Constructor.
     *
     * @param comparator the given comparator to compare T element.
     */
    public BinarySearchArray(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Binary search a given element target in an array in O(lgn) time.
     *
     * @param arr    the sorted array to be searched
     * @param target the searched element
     * @return the lower bound of the element the most less than target.
     * Aka, it shows how many elements are less than the target. Note that you also have to double check
     * if the returend value is 0, you have to check if arr[0] == target.
     */
    public int binarySearch(T[] arr, T target) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (this.comparator.compare(arr[mid], target) < 0) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}

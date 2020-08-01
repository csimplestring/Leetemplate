package com.csimplestring.algo.array;

/**
 * PrefixSumArray offers some static methods to calculate the prefix sum array.
 * The prefix sum array is a commonly-used technique to pre-process an array.
 *
 * <p>
 * given num = [1,2,3,-1,4] as input, its prefix sum array will be: sum = [1,3,6,5,9].
 * <p>
 * <p>
 * What is the usage of it?
 * sum[j] - sum[i] quickly gives the sum of elements a[i, j], which avoid O(n) time to calculate.
 */
public class PrefixSumArray {

    public static int[] sum(int[] num) {
        int[] res = new int[num.length];
        if (num.length == 0) {
            return res;
        }

        res[0] = num[0];
        for (int i = 1; i < num.length; i++) {
            res[i] = res[i - 1] + num[i];
        }

        return res;
    }
}


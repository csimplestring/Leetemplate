package com.csimplestring.algo.tree;

import java.util.*;

/**
 * In some OJ, the binary tree input usually is a string like: [1,null,2,3].
 * <p>
 * * This format is slightly different from what we are familiar with during Algorithm Courses.
 * <p>
 * * This class is an utility tool to de/serialize the binary tree, resulting helping you to debug when solving binary tree related problems.
 *
 * @param <T>
 * @see https://support.leetcode.com/hc/en-us/articles/360011883654-What-does-1-null-2-3-mean-in-binary-tree-representation-
 */
abstract public class BinaryTreeSerializer<T> {

    /**
     * the separator for serialized data, default is ','.
     */
    private String sep = ",";

    /**
     * serialize a Binary Tree into a string joined by separator.
     * <p>1,null,2,3</p>
     *
     * @param root
     * @return a string joined by separator in a BFS manner or empty when root is null.
     */
    public String serialize(TreeNode<T> root) {
        if (root == null) {
            return "";
        }

        List<TreeNode<T>> q = new LinkedList<>();
        q.add(root);

        List<String> tokens = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode<T> node = q.remove(0);
            String str = node == null ? "null" : this.serialiseElement(node.val);
            tokens.add(str);

            if (node != null) {
                q.add(node.left);
                q.add(node.right);
            }
        }

        while (tokens.get(tokens.size() - 1).equals("null")) {
            tokens.remove(tokens.size() - 1);
        }

        return String.join(",", tokens);
    }

    /**
     * deserialize a string in format '1,null,2,3' into a Binary Tree.
     *
     * @param data
     * @return the root node of a binary tree or null if the the input data string is blank.
     */
    public TreeNode<T> deserialize(String data) {
        if (data.isBlank()) {
            return null;
        }

        Deque<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(this.sep)));
        String firstToken = tokens.poll();

        TreeNode<T> root = new TreeNode<T>(this.deserialiseElement(firstToken));

        Deque<TreeNode<T>> nodeDeque = new ArrayDeque<>();
        nodeDeque.add(root);

        while (!nodeDeque.isEmpty()) {
            TreeNode<T> node = nodeDeque.poll();

            String ltoken = tokens.poll();
            if (ltoken != null && !ltoken.equals("null")) {
                TreeNode<T> lnode = new TreeNode<T>(this.deserialiseElement(ltoken));
                node.left = lnode;
                nodeDeque.add(lnode);
            }
            String rtoken = tokens.poll();
            if (rtoken != null && !rtoken.equals("null")) {
                TreeNode<T> rnode = new TreeNode<T>(this.deserialiseElement(rtoken));
                node.right = rnode;
                nodeDeque.add(rnode);
            }
        }

        return root;
    }

    /**
     * change the separator.
     *
     * @param sep
     */
    public void setSeparator(String sep) {
        this.sep = sep;
    }

    /**
     * You have override how to serialize the value in node.
     *
     * @param val
     * @return
     */
    abstract public String serialiseElement(T val);

    /**
     * You have override how to deserialize the string into you typed value.
     *
     * @param str
     * @return
     */
    abstract public T deserialiseElement(String str);

    /**
     * The Binary Tree Node.
     *
     * @param <S>
     */
    public static class TreeNode<S> {
        S val;
        TreeNode<S> left;
        TreeNode<S> right;

        TreeNode(S x) {
            val = x;
        }
    }
}

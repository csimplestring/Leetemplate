package com.csimplestring.algo.graph;

import java.util.*;

/**
 * TopologySortList receives the dependency-item and can sort elements in topological way.
 *
 * @param <T> the node type in a graph.
 */
public class TopologySortList<T> {

    /**
     * Node map, stores all the nodes in a graph
     */
    private final Map<T, Node<T>> nodes = new HashMap<>();

    /**
     * Out-going Node map, stores all the nodes that flowing out from a certain node.
     */
    private final Map<T, Set<T>> out = new HashMap<>();

    public TopologySortList() {
    }

    /**
     * Add the dependent pair into list. The order matters.
     * e.g., A -> B, meaning B depends on A.
     *
     * @param from the flow-in node, A
     * @param to   the flow-out node, B
     */
    public void add(T from, T to) {
        nodes.putIfAbsent(from, new Node<>(from));
        nodes.putIfAbsent(to, new Node<>(to));

        Node<T> eNode = nodes.get(to);
        eNode.changeInDegree(1);

        out.putIfAbsent(to, new HashSet<>());
        out.putIfAbsent(from, new HashSet<>());
        out.get(from).add(to);
    }

    /**
     * Topology sort elements.
     *
     * @return the topology sorted list, return empty list if topology sort can not succeed. Note that if there is no
     * elements in the list, an empty list is also returned. You need to distinguish such a situation at application.
     */
    public List<T> sort() {
        Deque<T> zeroInDegreeNodes = new ArrayDeque<>();
        nodes.values().forEach(tNode -> {
            if (tNode.inDegree == 0) {
                zeroInDegreeNodes.add(tNode.val);
            }
        });

        if (zeroInDegreeNodes.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> res = new ArrayList<>();
        while (!zeroInDegreeNodes.isEmpty()) {
            T t = zeroInDegreeNodes.poll();
            res.add(t);

            out.get(t).forEach(key -> {
                Node<T> next = nodes.get(key);
                next.changeInDegree(-1);
                if (next.inDegree == 0) {
                    zeroInDegreeNodes.add(key);
                }
            });
        }

        if (res.size() != nodes.keySet().size()) {
            return Collections.emptyList();
        }

        return res;
    }

    /**
     * Node represents a node in a graph, with inDegree value.
     *
     * @param <S>
     */
    public class Node<S> {
        /**
         * the original value
         */
        private final S val;

        /**
         * how many nodes flowing into this node, indicating the dependencies of the node
         */
        private int inDegree = 0;

        public Node(S val) {
            this.val = val;
        }

        /**
         * Change the inDegree
         *
         * @param offset can be positive or negative
         * @return the latest in-degree after applied
         */
        public int changeInDegree(int offset) {
            inDegree += offset;
            return inDegree;
        }
    }
}

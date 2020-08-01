package com.csimplestring.algo.graph;

import java.util.*;

/**
 * Find the cutting edge in a graph: the general idea is to DFS-visit each nodes:
 * <p>
 * A node contains 2 fields: timestamp, ancestor.
 * - initially, timestamp == ancestor;
 * - then do DFS visit
 * the node's timestamp = the order of DFS;
 * the node's ancestor = min (all the connected siblings' (except for its parent node) ancestor)
 * <p>
 * walk through each edge(n1, n2), if the n1.timestamp < n2.ancestor or n2.timestamp < n1.ancestor
 * then this edge is a cutting edge.
 *
 * @param <S>
 */
public class CuttingEdgeGraph<S> {

    private final Map<S, Node<S>> nodes = new HashMap<>();
    private final Set<S> visited = new HashSet<>();
    private final List<Edge<S>> edges = new ArrayList<>();
    private int counter = 0;

    public void addEdge(S s1, S s2) {
        this.edges.add(new Edge<>(s1, s2));

        Node<S> node1 = nodes.getOrDefault(s1, new Node<>(s1));
        Node<S> node2 = nodes.getOrDefault(s2, new Node<>(s2));

        node1.connected.add(node2);
        node2.connected.add(node1);
        nodes.put(s1, node1);
        nodes.put(s2, node2);
    }

    public List<Edge<S>> findCuttingEdge() {
        if (this.nodes.isEmpty()) {
            return Collections.emptyList();
        }

        Node<S> first = this.nodes.values().iterator().next();
        this.visit(first, null);

        List<Edge<S>> cuttingEdges = new ArrayList<>();
        for (Edge<S> edge : this.edges) {
            Node<S> n1 = this.nodes.get(edge.start);
            Node<S> n2 = this.nodes.get(edge.end);
            if (n1.ts < n2.ancestor || n2.ts < n1.ancestor) {
                cuttingEdges.add(edge);
            }
        }

        return cuttingEdges;
    }

    private int visit(Node<S> root, Node<S> parent) {
        if (visited.contains(root.value)) {
            return root.ancestor;
        }

        visited.add(root.value);
        root.ts = counter++;
        root.ancestor = root.ts;

        int minAncestor = root.ancestor;
        for (Node<S> node : root.connected) {
            if (parent == null) {
                minAncestor = Math.min(minAncestor, visit(node, root));
            }
            if (parent != null && !parent.equals(node)) {
                minAncestor = Math.min(minAncestor, visit(node, root));
            }
        }
        root.ancestor = minAncestor;
        return root.ancestor;
    }

    private static class Node<T> {
        public T value;
        public Set<Node<T>> connected = new HashSet<>();
        public int ts = 0;
        public int ancestor = 0;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class Edge<T> {
        public T start;
        public T end;

        public Edge(T start, T end) {
            this.start = start;
            this.end = end;
        }
    }
}

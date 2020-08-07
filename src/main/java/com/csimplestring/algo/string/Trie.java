package com.csimplestring.algo.string;

/**
 * Trie is used to build a string trie structure to quickly search if a word exists in the given words dictionary.
 */
public class Trie {

    private Node root;

    public Trie(String[] words) {
        this.root = new Node();
        for (String word : words) {
            build(word);
        }
    }

    private void build(String str) {
        Node node = this.root;
        for (char c : str.toCharArray()) {
            Node next = node.getOrDefault(c);
            node = next;
        }
        node.word = str;
    }

    public Node getRoot() {
        return this.root;
    }

    static class Node {
        String word;
        Node[] next;

        public Node(Node[] next) {
            this.next = next;
        }

        public Node() {
            this.next = new Node[26];
        }

        public Node getOrDefault(char ch) {
            if (this.next[ch - 'a'] == null) {
                this.next[ch - 'a'] = new Node();
            }

            return this.next[ch - 'a'];
        }
    }
}

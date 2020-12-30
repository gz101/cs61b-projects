import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

/**
 * Implementation of a Trie Set based on the TrieSet61B interface.
 * UC Berkeley's CS61B Lab 9: Tries, 2019.
 * @author Gabriel Chiong
 */
public class MyTrieSet implements TrieSet61B {

    private Node root; // Root of trie.

    /** Trie node backed by Java HashMap. */
    private static class Node {
        private char val;
        private boolean isKey;
        private HashMap<Character, Node> map;

        /** No argument constructor. */
        private Node() {
            isKey = false;
            map = new HashMap<>();
        }

        /** Two argument constructor. */
        private Node(char c, boolean b) {
            val = c;
            isKey = b;
            map = new HashMap<>();
        }
    }

    /** Constructor for Trie (empty string symbol table). */
    public MyTrieSet() {
        root = new Node();
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node();
    }

    /** Helper method to validate keys. */
    private void validateKey(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException("Key must be a string longer than 1 character.");
        }
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        validateKey(key);
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }

        /** Check if final node is a key. */
        if (curr.isKey) {
            return true;
        } else {
            return false;
        }
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        validateKey(key);
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        validateKey(prefix);
        List<String> results = new ArrayList<>();
        Node curr = root;

        /** Bring curr ptr to end of prefix and return empty list if cannot find prefix. */
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                return results;
            }
        }

        /** Check if prefix itself is a word. */
        if (curr.isKey) {
            results.add(prefix);
        }

        collect(curr, new StringBuilder(prefix), results);
        return results;
    }

    /** Helper method to find all matching prefixes. */
    private void collect(Node curr, StringBuilder prefix, List<String> results) {
        if (curr == null) {
            return;
        }
        if (curr.isKey) {
            results.add(prefix.toString());
        }
        for (char c : curr.map.keySet()) {
            prefix.append(c);
            collect(curr.map.get(c), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
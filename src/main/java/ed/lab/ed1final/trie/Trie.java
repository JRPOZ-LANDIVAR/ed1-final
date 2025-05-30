package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int wordCount = 0;     //contadores de palabras
        int prefixCount = 0;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
            current.prefixCount++;
        }
        current.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return 0;
            }
            current = current.children.get(ch);
        }
        return current.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return 0;
            }
            current = current.children.get(ch);
        }
        return current.prefixCount;
    }

    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) return;

        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode next = current.children.get(ch);
            next.prefixCount--;
            current = next;
        }
        current.wordCount--;
    }
}